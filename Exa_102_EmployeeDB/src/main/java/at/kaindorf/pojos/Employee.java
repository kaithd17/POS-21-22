package at.kaindorf.pojos;

import at.kaindorf.json.LocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @JsonProperty("emp_no")
    private int employeeNo;

    @Column(name = "first_name", length = 14, nullable = false)
    @NotBlank(message = "You have to enter a firstname")
    @Size(min = 3, max = 14, message = "The Firstname has to be between 3 and 14 characters")
    private String firstname;

    @Column(name = "last_name", length = 16, nullable = false)
    @NotBlank(message = "You have to enter a lastname")
    @Size(min = 4, max = 16, message = "The Firstname has to be between 4 and 16 characters")
    private String lastname;

    @Column(name = "gender")
    private Gender gender;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonProperty("birthDate")
    @Column(name = "birth_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Date of birth is required")
    @Past(message = "The date has to be in the past")
    private LocalDate dateOfBirth;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @JsonIgnore
    @JoinColumn(name = "dept_no")
    private Department department;
}
