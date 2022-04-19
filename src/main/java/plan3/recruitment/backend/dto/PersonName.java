package plan3.recruitment.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PersonName implements Serializable {

    @JsonProperty
    public String firstName;

    @JsonProperty
    public String lastName;

    public PersonName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public PersonName(){}
    @Override
    public String toString() {
        return "PersonName{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
