package pedrozc90.models.included;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class BookTuple {
    public final Long id;
    public final String title;

    @QueryProjection
    public BookTuple(final Long id, final String title) {
        this.id = id;
        this.title = title;
    }
}
