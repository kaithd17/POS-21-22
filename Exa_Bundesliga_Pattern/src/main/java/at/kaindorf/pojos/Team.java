package at.kaindorf.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private String name;
    private Integer position;
    private Integer played;
    private Integer won;
    private Integer draw;
    private Integer loss;
    private Integer gs;
    private Integer ga;
    private Integer gd;
    private Integer pts;
}
