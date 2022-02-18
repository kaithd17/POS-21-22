package at.kaindorf.exa_104_springrest_examdb.api;

import at.kaindorf.exa_104_springrest_examdb.database.SubjectRepository;
import at.kaindorf.exa_104_springrest_examdb.pojos.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectRepository.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }
}
