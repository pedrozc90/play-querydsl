package pedrozc90.controllers;

import pedrozc90.dtos.BookDto;
import pedrozc90.services.BookService;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class BooksController extends Controller {

    @Transactional(readOnly = true)
    public static Result list(final String title) {
        final List<BookDto> result = BookService.findByTitleContaining(title);
        return ok(Json.toJson(result));
    }
}
