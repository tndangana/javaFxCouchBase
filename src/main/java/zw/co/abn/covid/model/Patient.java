package zw.co.abn.covid.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends BaseId{

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;


    public Patient(String firstName, String lastName, String gender, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        if (!super.equals(o)) return false;
        Patient patient = (Patient) o;
        return getFirstName().equals(patient.getFirstName()) &&
                getLastName().equals(patient.getLastName()) &&
                getDateOfBirth().equals(patient.getDateOfBirth()) &&
                getGender().equals(patient.getGender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFirstName(), getLastName(), getDateOfBirth(), getGender());
    }
}
