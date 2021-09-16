package at.kaindorf.intro.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentPK implements Serializable {
    private String className;
    private Long catNo;
}
