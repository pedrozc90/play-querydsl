package pedrozc90.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pedrozc90.dtos.AuthorDto;
import pedrozc90.models.included.Author;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDto toDto(Author author);
}
