package plan3.recruitment.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Person implements Serializable {

    @JsonProperty
    public  String email;

    @JsonProperty
    public  PersonName personName;

    public Person(String email, PersonName personName) {
        this.email = email;
        this.personName = personName;
    }
    public Person(){}
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
