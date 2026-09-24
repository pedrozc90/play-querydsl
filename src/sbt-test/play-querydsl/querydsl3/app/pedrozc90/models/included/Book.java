package pedrozc90.models.included;

import pedrozc90.enums.Genre;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Book {
    @Id @GeneratedValue public Long id;

    @Column(name = "title")
    public String title;

    // outside the scanned package, resolved through -sourcepath
    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    public Genre genre;

    // JPA 2.0 has no foreignKey attribute on @JoinColumn
    @ManyToOne
    @JoinColumn(name = "author_id")
    @ForeignKey(name = "fk_book_author")
    private Author author;
}
