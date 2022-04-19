package plan3.recruitment.backend.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger logger = LogManager.getLogger(PersonServiceImpl.class);
    @Autowired
    public PersonRepository personRepository;

    public HttpEntity<?> fetchPersonData(String email) {
        PersonEntity personEntity = personRepository.findByEmail(email);
        logger.info("Person data is fetched from the database ");
        if (personEntity == null) {
            return new ResponseEntity<>(new CustomErrorMessage(CustomError.NO_DATA_FOUND, "404"), HttpStatus.NOT_FOUND);
        }
        PersonName personName = new PersonName(personEntity.firstName, personEntity.lastName);
        Person person = new Person(personEntity.email, personName);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @Transactional
    public boolean createPerson(Person person) {
        logger.info("Adding the person data in the database");
        PersonEntity checkIfPersonExists = personRepository.findByEmail(person.email);
        if (checkIfPersonExists != null) {
            return false;
        }
        PersonEntity personEntity = new PersonEntity();
        personEntity.email = person.email;
        personEntity.firstName = person.personName.firstName;
        personEntity.lastName = person.personName.lastName;
        personRepository.save(personEntity);
        return true;
    }

    @Transactional
    public boolean removePerson(String email) {
        logger.info("Removing the Person data from the database ");
        PersonEntity personEntity = personRepository.findByEmail(email);
        if (personEntity == null) {
            return false;
        }
        personRepository.delete(personEntity);
        return true;
    }

    @Transactional
    public List<Person> fetchAll() {
        logger.info("Fetching all Person data ");
        List<Person> personList = new LinkedList<>();
        List<PersonEntity> personEntityList = personRepository.findAll();
            for (PersonEntity p : personEntityList) {
                PersonName personName = new PersonName(p.firstName, p.lastName);
                Person person = new Person(p.email, personName);
                personList.add(person);
            }
            return personList;
    }

    @Override
    public boolean updatePerson(String email, Person person) {
        logger.info("Updating the person data in database ");
        PersonEntity entity = personRepository.findByEmail(email);
        if (entity == null) {
            return false;
        }
        entity.firstName = person.personName.firstName;
        entity.lastName = person.personName.lastName;
        personRepository.save(entity);
        return true;
    }

}
