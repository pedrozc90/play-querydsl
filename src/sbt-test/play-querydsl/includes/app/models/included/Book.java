package models.included;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book {
    @Id public Long id;

    public String title;
}
