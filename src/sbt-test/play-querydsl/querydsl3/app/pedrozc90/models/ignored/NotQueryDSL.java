package pedrozc90.models.ignored;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class NotQueryDSL {
    @Id @GeneratedValue public Long id;

    @Column(name = "ignored")
    public String ignored;
}
