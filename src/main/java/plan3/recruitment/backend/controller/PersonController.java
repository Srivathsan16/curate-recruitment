package plan3.recruitment.backend.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plan3.recruitment.backend.dto.Person;
import plan3.recruitment.backend.error.CustomError;
import plan3.recruitment.backend.error.CustomErrorMessage;
import plan3.recruitment.backend.services.PersonServiceImpl;

import java.util.List;
import java.util.regex.Pattern;

@RestController
public class PersonController {


    private static final Logger logger = LogManager.getLogger(PersonController.class);
    private static final java.util.regex.Pattern EMAIL_PATTERN = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-Z)]+\\.[(a=zA-z)]{2,3}$");

    @Autowired
    private PersonServiceImpl personService;

    @GetMapping(value = "/findByEmail/{email}")
    public HttpEntity<? extends Object> fetch(@PathVariable("email") String email) {
       if(!requestValidator(email)) {
           logger.error("Email is not valid, please provide valid email {}", email);
           return new ResponseEntity<>(new CustomErrorMessage(CustomError.INVALID_EMAIL, "400"),HttpStatus.BAD_REQUEST);
       }
        return personService.fetchPersonData(email);
    }

    @PostMapping(value="/save")
    public HttpEntity<? extends Object> savePersonDetails(@RequestBody Person person) {
        System.out.println("Person recieved is >>> " + person);
        if (!requestValidator(person.email)) {
            return new ResponseEntity<>(new CustomErrorMessage(CustomError.INVALID_EMAIL, "400"),HttpStatus.BAD_REQUEST);
        }
        personService.save(person);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

    @PostMapping(value="/persons/remove/{email}")
    public ResponseEntity<Boolean> removePerson(@PathVariable("email") String email){
        System.out.println("Person that is going to be removed is >>> " + email);
        if(personService.removePerson(email))
            return new ResponseEntity<>(true,HttpStatus.OK);
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/findAll")
    public List<Person> fetchAll() {
        System.out.println("Inside fetchALl method");
        return personService.fetchAll();
    }

    //TODO
    @PutMapping(value = "/update/{email}")
    public void updatePerson() {

    }

    private boolean requestValidator(String email) {
            if(!email.isBlank() && !EMAIL_PATTERN.matcher(email).matches()) {
                logger.error("Email {} is not valid ", email);
                return false;
        }
        return true;
    }
}
