import pedrozc90.models.included.BookTuple;
import pedrozc90.models.included.QAuthor;
import pedrozc90.models.included.QBook;
import pedrozc90.models.included.QBookTuple;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class QueryDSLTest {

    @Test
    public void generatesQueryTypeForIncludedPackage() {
        assertEquals("title", QBook.book.title.getMetadata().getName());
    }

    @Test
    public void generatesQueryTypeForLombokEntity() {
        assertEquals("name", QAuthor.author.name.getMetadata().getName());
    }

    @Test
    public void generatesProjectionForQueryProjectionConstructor() {
        QBookTuple tuple = new QBookTuple(QBook.book.id, QBook.book.title);
        assertEquals(BookTuple.class, tuple.getType());
        assertEquals(2, tuple.getArgs().size());
    }

    @Test
    public void resolvesTypesOutsideScannedPackage() {
        assertEquals("genre", QBook.book.genre.getMetadata().getName());
    }

    @Test
    public void skipsPackagesOutsideQueryDSLPackage() {
        try {
            Class.forName("pedrozc90.models.ignored.QNotQueryDSL");
            fail("QNotQueryDSL should not be generated");
        } catch (ClassNotFoundException expected) {
        }
    }
}
