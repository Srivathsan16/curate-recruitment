package plan3.recruitment.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import plan3.recruitment.backend.entity.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, String> {

    PersonEntity findByEmail(String email);

}
