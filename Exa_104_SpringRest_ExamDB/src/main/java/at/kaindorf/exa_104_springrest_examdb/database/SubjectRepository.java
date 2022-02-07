package at.kaindorf.exa_104_springrest_examdb.database;

import at.kaindorf.exa_104_springrest_examdb.pojos.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}