package plan3.recruitment.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plan3.recruitment.backend.dto.Person;
import plan3.recruitment.backend.dto.PersonName;
import plan3.recruitment.backend.entity.PersonEntity;
import plan3.recruitment.backend.error.CustomError;
import plan3.recruitment.backend.error.CustomErrorMessage;
import plan3.recruitment.backend.repositories.PersonRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    public PersonRepository personRepository;

    public HttpEntity<? extends Object> fetchPersonData(String email) {
        System.out.println("Email is >>> " + email);
        PersonEntity personEntity =  personRepository.findByEmail(email);
        if(personEntity==null){
            return new ResponseEntity<>(new CustomErrorMessage(CustomError.NO_DATA_FOUND, "404"),HttpStatus.NOT_FOUND);
        }
        PersonName personName = new PersonName(personEntity.firstName,personEntity.lastName);
        Person person = new Person(personEntity.email,personName);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @Transactional
    public void save(Person person) {
        System.out.println("in save method");
        PersonEntity personEntity = new PersonEntity();
        personEntity.email=person.email;
        personEntity.firstName = person.personName.firstName;
        personEntity.lastName = person.personName.lastName;
        personRepository.save(personEntity);
    }

    @Transactional
    public boolean removePerson(String email) {
        System.out.println("In Remove method >>>>>");
        PersonEntity personEntity = personRepository.findByEmail(email);
        if(personEntity==null){
            return false;
        }
        personRepository.delete(personEntity);
        return true;
    }

    @Transactional
    public List<Person> fetchAll() {
        //TODO> Add logger
        List<Person> personList = new LinkedList<>();
        List<PersonEntity> personEntityList = personRepository.findAll();
        for(PersonEntity p: personEntityList){
            PersonName personName = new PersonName(p.firstName,p.lastName);
            Person person = new Person(p.email,personName);
            personList.add(person);
        }
        return personList;
    }

}
