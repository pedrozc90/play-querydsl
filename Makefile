.DEFAULT_GOAL := help

SHELL := bash
.SHELLFLAGS := -eu -o pipefail -c

# -- help ---------------------

.PHONY: help
help:
	@grep -E '^[a-zA-Z_-]+:.*## .*$$' $(MAKEFILE_LIST) \
		| awk 'BEGIN {FS = ":.*?## "}; {printf "  \033[36m%-20s\033[0m %s\n", $$1, $$2}'

# runs tools from mise.toml even when mise is not activated in the shell
RUN := mise exec --
SBT := $(RUN) sbt -batch

# -- setup --------------------

.PHONY: install
install: ## install toolchain (java, sbt, scalafmt)
	mise install

# -- build --------------------

.PHONY: build
build: ## compile the plugin
	$(SBT) compile

.PHONY: publish-local
publish-local: ## publish the plugin to the local ivy repository
	$(SBT) publishLocal

.PHONY: test
test: ## run the plugin scripted tests
	$(SBT) scripted

.PHONY: clean
clean: ## remove build outputs
	$(SBT) clean compile

# -- format -------------------

.PHONY: fmt
fmt: ## format Scala, sbt and Java sources
	$(RUN) scalafmt
	$(SBT) javafmt

.PHONY: fmt-check
fmt-check: ## fail if sources are not formatted
	$(RUN) scalafmt --check
	$(SBT) javafmtCheck

# -- ci -----------------------

.PHONY: check
check: fmt-check test ## run everything CI runs before publishing
