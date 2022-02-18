package at.kaindorf.exa_104_springrest_examdb.database;

import at.kaindorf.exa_104_springrest_examdb.pojos.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("SELECT s FROM Subject  s ORDER BY s.longname")
    List<Subject> getAllSubjects();

}