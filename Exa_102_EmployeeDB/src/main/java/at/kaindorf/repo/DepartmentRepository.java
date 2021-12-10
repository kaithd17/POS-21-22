package at.kaindorf.repo;

import at.kaindorf.pojos.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, String> {

    @Query("select d from Department d where d.deptName = :name")
    Department getDepartmentByDeptName(@Param("name") String name);

}