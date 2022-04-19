package plan3.recruitment.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {

    @JsonProperty
    public  String email;

    @JsonProperty
    public  PersonName personName;

    public Person(String email, PersonName personName) {
        this.email = email;
        this.personName = personName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonName getPersonName() {
        return personName;
    }

    public void setPersonName(PersonName personName) {
        this.personName = personName;
    }

    public static Person valueOf(final String email,final PersonName personName) {
        return new Person(email,personName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "email='" + email + '\'' +
                ", personName=" + personName +
                '}';
    }
}
