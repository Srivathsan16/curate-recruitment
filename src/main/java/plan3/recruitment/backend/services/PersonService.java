package plan3.recruitment.backend.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import plan3.recruitment.backend.dto.Person;

import java.util.List;

public interface PersonService {

    public HttpEntity<? extends Object> fetchPersonData(String email);
    public void save(Person person);
    public boolean removePerson(String email) ;
    public List<Person> fetchAll();
}
