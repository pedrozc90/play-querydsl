package pedrozc90.dtos;

import lombok.Data;
import pedrozc90.enums.Genre;

@Data
public class BookDto {
    public final Long id;
    public final String title;
    public final Genre genre;
    public final AuthorDto author;
}
