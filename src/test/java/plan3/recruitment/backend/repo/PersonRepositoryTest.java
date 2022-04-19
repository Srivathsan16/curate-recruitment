package plan3.recruitment.backend.repo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import plan3.recruitment.backend.entity.PersonEntity;
import plan3.recruitment.backend.repositories.PersonRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void test_whenFetchAll() {
        PersonEntity personEntity = new PersonEntity();
        personEntity.email="sritest@gmail.com";
        personEntity.firstName = "Sri";
        personEntity.lastName = "test";

        PersonEntity personEntity2 = new PersonEntity();
        personEntity2.email = "srivathsan@gmail.com";
        personEntity2.firstName = "Sri";
        personEntity2.lastName = "Vathsan";
        personRepository.save(personEntity);
        personRepository.save(personEntity2);

        List<PersonEntity> result = new ArrayList<>();
        Iterable<PersonEntity> iterablePerson = personRepository.findAll();
        iterablePerson.forEach(result::add);
        assertEquals(result.size(), 2);
    }

    @Test
    public void test_whenFindByEmail() {
        PersonEntity personEntity = new PersonEntity();
        personEntity.email="sritest@gmail.com";
        personEntity.firstName = "Sri";
        personEntity.lastName = "test";
        personRepository.save(personEntity);

        PersonEntity result = personRepository.findByEmail(personEntity.email);
        assertEquals(result.lastName, personEntity.lastName);
    }

    @Test
    public void test_removePersonDetails() {
        PersonEntity personEntity = new PersonEntity();
        personEntity.email="sritest@gmail.com";
        personEntity.firstName = "Sri";
        personEntity.lastName = "test";
        personRepository.save(personEntity);
        assertNotNull(personEntity);

        personRepository.delete(personEntity);
        assertEquals(personRepository.findAll(), Collections.EMPTY_LIST);
    }
}
