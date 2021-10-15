package at.kaindorf.springintro;

import at.kaindorf.springintro.beans.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("/stu")
    public Student getStudent(){
        return new Student("Bart", "Simpson", 10);
    }
}
