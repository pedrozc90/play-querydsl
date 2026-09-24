package pedrozc90.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pedrozc90.dtos.BookDto;
import pedrozc90.models.included.Book;

@Mapper(uses = {AuthorMapper.class})
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto toDto(Book entity);
}
