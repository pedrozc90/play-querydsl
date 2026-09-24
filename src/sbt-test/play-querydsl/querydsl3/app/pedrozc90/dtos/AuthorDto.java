package pedrozc90.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorDto implements Serializable {
    private final Long id;
    private final String name;
}
