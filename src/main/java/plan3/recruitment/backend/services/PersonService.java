package plan3.recruitment.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plan3.recruitment.backend.dto.Person;
import plan3.recruitment.backend.entity.PersonEntity;
import plan3.recruitment.backend.repositories.PersonRepository;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    public PersonRepository personRepository;

    public PersonEntity getPersonData(String email) {
        System.out.println("Email is >>> " + email);
        return personRepository.findByEmail(email);
    }

    @Transactional
    public void save(Person person) {
        System.out.println("in save method");
        PersonEntity personEntity = new PersonEntity();
        personEntity.setEmail(person.email);
        personEntity.setFirstName(person.personName.firstName);
        personEntity.setLastName(person.personName.lastName);
        personRepository.save(personEntity);
    }

    @Transactional
    public boolean removePerson(Person person) {
        System.out.println("In Remove method >>>>>");
        PersonEntity personEntity = personRepository.findByEmail(person.email);
        if(personEntity==null){
            return false;
        }
        personRepository.delete(personEntity);
        return true;
    }

    @Transactional
    public Iterable<PersonEntity> fetchAll() {
        System.out.println("In FetchALl method");
        return personRepository.findAll();
    }

}
