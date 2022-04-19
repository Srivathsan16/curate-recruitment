package plan3.recruitment.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersonEntity {

    @Id
    public String email;

    public String firstName;

    public String lastName;


}
