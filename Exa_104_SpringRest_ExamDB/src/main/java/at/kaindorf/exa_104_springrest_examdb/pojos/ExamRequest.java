package at.kaindorf.exa_104_springrest_examdb.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExamRequest {
    private Long studentId;
    private LocalDate dateOfExam;
    private Integer duration;
    private Long subjectId;
}
