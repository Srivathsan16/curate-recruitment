package plan3.recruitment.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonName {

    @JsonProperty
    public String firstName;

    @JsonProperty
    public String lastName;

    public PersonName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "PersonName{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
