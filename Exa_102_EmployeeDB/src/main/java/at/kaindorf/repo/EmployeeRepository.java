package at.kaindorf.repo;

import at.kaindorf.pojos.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("select e from Employee e where e.department.deptName = :name ORDER BY e.lastname, e.firstname")
    List<Employee> findEmployeesByDepartmentSorted(@Param("name") String name);

    @Query("select e from Employee e where e.department.deptName = :name ORDER BY e.lastname DESC , e.firstname DESC ")
    List<Employee> findEmployeesByDepartment(@Param("name") String name);

    @Query("SELECT MAX(e.employeeNo) FROM Employee e WHERE e.department.deptManager.employeeNo <> e.employeeNo")
    int getLastEmpNo();
}