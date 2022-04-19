package plan3.recruitment.backend.services;

import org.springframework.http.HttpEntity;
import plan3.recruitment.backend.dto.Person;

import java.util.List;

public interface PersonService {

     HttpEntity<? extends Object> fetchPersonData(String email);
     boolean createPerson(Person person);
     boolean removePerson(String email) ;
     List<Person> fetchAll();
     boolean updatePerson(String email, Person person);
}
