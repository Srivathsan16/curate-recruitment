package plan3.recruitment.backend.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plan3.recruitment.backend.dto.Person;
import plan3.recruitment.backend.entity.PersonEntity;
import plan3.recruitment.backend.services.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    private static final Logger logger = LogManager.getLogger(PersonController.class);
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/findByEmail/{email}")
    public PersonEntity fetch(@PathVariable("email") String email) {
       logger.info("Incoming email Id is >> {}",email );
      return personService.getPersonData(email);
    }

    @PostMapping(value="/save")
    public void savePersonDetails(@RequestBody Person person) {
        System.out.println("Person recieved is >>> " + person);
        personService.save(person);
    }

    @PostMapping(value="/remove")
    public boolean removePerson(@RequestBody Person person){
        System.out.println("Person that is going to be removed is >>> " + person);
        return personService.removePerson(person);
    }

    @GetMapping(value = "/findAll")
    public Iterable<PersonEntity> fetchAll() {
        return personService.fetchAll();
    }

    //TODO
    @PutMapping(value = "/update/{email}")
    public void updatePerson() {

    }
}
