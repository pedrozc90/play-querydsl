package pedrozc90.repositories;

import static org.junit.Assert.assertEquals;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.start;
import static play.test.Helpers.stop;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import pedrozc90.enums.Genre;
import pedrozc90.models.included.Book;
import org.junit.Test;
import play.db.jpa.JPA;
import play.test.FakeApplication;

public class BookRepositoryTest {

    @Test
    public void findsBooksByTitleFragment() throws Throwable {
        FakeApplication app = fakeApplication();
        start(app);
        try {
            List<String> titles =
                    JPA.withTransaction(
                            () -> {
                                for (String title :
                                        Arrays.asList(
                                                "Lorem ipsum", "Dolor sit amet", "Ipsum lorem")) {
                                    Book book = new Book();
                                    book.title = title;
                                    book.genre = Genre.FICTION;
                                    JPA.em().persist(book);
                                }
                                return BookRepository.findByTitleContaining("ipsum").stream()
                                        .map(b -> b.title + ":" + b.genre)
                                        .collect(Collectors.toList());
                            });
            // H2 LIKE is case-sensitive, so "Ipsum lorem" is excluded
            assertEquals(Arrays.asList("Lorem ipsum:FICTION"), titles);
        } finally {
            stop(app);
        }
    }
}
