package at.kaindorf.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    private Integer round;
    private String team1;
    private String team2;
    private Integer goal1;
    private Integer goal2;
}
