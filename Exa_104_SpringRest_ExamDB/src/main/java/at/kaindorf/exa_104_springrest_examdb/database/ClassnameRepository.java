package at.kaindorf.exa_104_springrest_examdb.database;

import at.kaindorf.exa_104_springrest_examdb.pojos.Classname;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassnameRepository extends JpaRepository<Classname, Long> {
}