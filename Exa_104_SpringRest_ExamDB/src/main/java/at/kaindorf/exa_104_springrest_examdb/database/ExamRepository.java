package at.kaindorf.exa_104_springrest_examdb.database;

import at.kaindorf.exa_104_springrest_examdb.pojos.Exam;
import at.kaindorf.exa_104_springrest_examdb.pojos.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {


    @Query("select e from Exam e where e.student = :student")
    List<Exam> findAllByStudent(@Param("student") Student student);
}