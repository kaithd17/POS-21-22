package at.kaindorf.exa_104_springrest_examdb.database;

import at.kaindorf.exa_104_springrest_examdb.pojos.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}