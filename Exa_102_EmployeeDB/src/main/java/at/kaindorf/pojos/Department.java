package at.kaindorf.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department {
    @Id
    @JsonProperty("number")
    @Column(name = "dept_no", length = 4)
    private String deptNo;
    @JsonProperty("name")
    @JoinColumn(name = "dept_name")
    private String deptName;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "emp_no")
    private Employee deptManager;

    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
    private List<Employee> employees = new ArrayList<>();

    //To add a new Employee
    public void addEmployee(Employee employee) {
        employee.setDepartment(this);
        employees.add(employee);
    }
}
