# Changelog

All notable changes to this project are documented in this file.
The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - Unreleased

### Changed

- Versioning: plugin 1.x supports sbt 0.13; plugin 2.x will support sbt 1.x.

### Added

- `queryDSLGenerate` task to generate `Q*` sources without compiling.
- sbt scripted tests for the plugin. The Play sample project now runs as the `querydsl3` and `querydsl4` scripted tests.

### Removed

- `queryDSLExcludes`. Use `queryDSLIncludes`, or annotate the entity with `@QueryExclude`.
