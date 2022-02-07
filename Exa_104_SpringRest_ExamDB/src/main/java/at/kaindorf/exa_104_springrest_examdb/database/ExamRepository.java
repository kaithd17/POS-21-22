package at.kaindorf.exa_104_springrest_examdb.database;

import at.kaindorf.exa_104_springrest_examdb.pojos.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}