package at.kaindorf.pojos;

import at.kaindorf.json.GenderDeserializer;
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
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @JsonProperty("emp_no")
    private int employeeNo;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    //@JsonDeserialize(using = GenderDeserializer.class)
    private Gender gender;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonProperty("birthDate")
    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @JsonIgnore
    @JoinColumn(name = "dept_no")
    private Department department;
}
