package at.kaindorf.json;

import at.kaindorf.pojos.Department;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JSONAccess {

    private static final Path path = Paths.get(System.getProperty("user.dir"),"src", "main", "resources", "employeedb.json");

    public static List<Department> getEmployeeData() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Department> departments = mapper.readValue(path.toFile(), new TypeReference<List<Department>>() { });
            //Set departments
            departments.forEach(department -> {
                department.getDeptManager().setDepartment(department);
                department.getEmployees().forEach(employee -> {
                    employee.setDepartment(department);
                });
            });
            return departments;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
