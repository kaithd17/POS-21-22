package at.kaindorf.repo;

import at.kaindorf.pojos.Department;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class InitDB {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    private static final Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "employeedb.json");

    @PostConstruct
    public void saveEmployeeData() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Department> departments = mapper.readValue(path.toFile(), new TypeReference<List<Department>>() {
            });
            System.out.println(departments.size());
            //Set departments
            departments.forEach(department -> {
                employeeRepository.saveAndFlush(department.getDeptManager());
                department.getDeptManager().setDepartment(department);

                employeeRepository.saveAllAndFlush(department.getEmployees());
                department.getEmployees().forEach(employee -> {
                    employee.setDepartment(department);
                });
            });
            departmentRepository.saveAllAndFlush(departments);

            //Saves employees with the right references
            departments.forEach(department -> {
                employeeRepository.saveAndFlush(department.getDeptManager());
                employeeRepository.saveAllAndFlush(department.getEmployees());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
