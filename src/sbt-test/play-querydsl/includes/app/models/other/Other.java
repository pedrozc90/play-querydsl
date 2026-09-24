package models.other;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Other {
    @Id public Long id;

    public String title;
}
