package at.kaindorf.exa_104_springrest_examdb.api;

import at.kaindorf.exa_104_springrest_examdb.database.StudentRepository;
import at.kaindorf.exa_104_springrest_examdb.pojos.Classname;
import at.kaindorf.exa_104_springrest_examdb.pojos.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/getStudents")
    public ResponseEntity<Page<Student>> getStudentsFromClass (@RequestParam(value = "page", defaultValue = "0") int pageNo, @RequestParam("classname") String classname) {
        Pageable page = PageRequest.of(pageNo, 10, Sort.by("lastname").descending());
        Page<Student> students = studentRepository.findStudentByClassName(classname, page);
        System.out.println(students.getContent());
        return ResponseEntity.of(Optional.of(students));
    }
}
