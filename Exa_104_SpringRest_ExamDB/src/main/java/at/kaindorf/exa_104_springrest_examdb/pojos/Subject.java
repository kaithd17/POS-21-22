package at.kaindorf.exa_104_springrest_examdb.pojos;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Subject {
    @Id
    @Column(name = "subject_id", nullable = false)
    private Long subjectId;

    @Column(name = "longname", length = 100)
    private String longname;

    @Column(name = "shortname", length = 10)
    private String shortname;

    @Column(name = "written", nullable = false)
    private Boolean written;

    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private List<Exam> exams = new ArrayList<>();
}
