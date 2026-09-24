package pedrozc90.services;

import pedrozc90.dtos.BookDto;
import pedrozc90.mappers.BookMapper;
import pedrozc90.models.included.Book;
import pedrozc90.repositories.BookRepository;
import play.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class BookService {

    private static final Logger.ALogger logger = Logger.of(BookService.class);
    private static final BookMapper mapper = BookMapper.INSTANCE;

    public static List<BookDto> findByTitleContaining(final String fragment) {
        final List<Book> list = BookRepository.findByTitleContaining(fragment);
        logger.debug("Loaded {} books", list.size());
        return list.stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
