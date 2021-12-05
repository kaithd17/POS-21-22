package at.kaindorf.pojos;

import at.kaindorf.json.GenderDeserializer;
import at.kaindorf.json.LocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @JsonProperty("emp_no")
    private int employeeNo;
    @NotNull
    private String firstname;
    private String lastname;
    @JsonDeserialize(using = GenderDeserializer.class)
    private Gender gender;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonProperty("birthDate")
    private LocalDate dateOfBirth;

    @ManyToOne
    @ToString.Exclude
    private Department department;
}
