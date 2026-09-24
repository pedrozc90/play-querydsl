package pedrozc90.mappers;

import static org.junit.Assert.assertEquals;

import pedrozc90.dtos.AuthorDto;
import pedrozc90.models.included.Author;
import org.junit.Test;

public class AuthorMapperTest {

    @Test
    public void mapsLombokEntityToDto() {
        Author author = new Author();
        author.setId(1L);
        author.setName("Lorem");

        AuthorDto dto = AuthorMapper.INSTANCE.toDto(author);

        assertEquals(Long.valueOf(1L), dto.getId());
        assertEquals("Lorem", dto.getName());
    }
}
