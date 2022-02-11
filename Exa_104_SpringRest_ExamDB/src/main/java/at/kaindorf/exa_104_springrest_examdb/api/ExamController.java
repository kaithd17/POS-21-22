package at.kaindorf.exa_104_springrest_examdb.api;


import at.kaindorf.exa_104_springrest_examdb.database.ExamRepository;
import at.kaindorf.exa_104_springrest_examdb.database.StudentRepository;
import at.kaindorf.exa_104_springrest_examdb.pojos.Exam;
import at.kaindorf.exa_104_springrest_examdb.pojos.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/getExams")
    public ResponseEntity<List<Exam>> getExamsFromStudent(@RequestParam("studentId") Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        List<Exam> exams = examRepository.findAllByStudent(student);
        return ResponseEntity.of(Optional.of(exams));
    }
}
