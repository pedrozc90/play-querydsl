package pedrozc90.repositories;

import com.querydsl.jpa.impl.JPAQuery;
import pedrozc90.models.included.Book;
import pedrozc90.models.included.QBook;
import play.db.jpa.JPA;

import java.util.List;

public class BookRepository {

    private static final QBook book = QBook.book;

    public static List<Book> findByTitleContaining(final String fragment) {
        return new JPAQuery<Book>(JPA.em())
                .from(book)
                .where(book.title.contains(fragment))
                .orderBy(book.title.asc())
                .fetch();
    }
}
