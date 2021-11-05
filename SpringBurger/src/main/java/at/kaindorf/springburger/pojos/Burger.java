package at.kaindorf.springburger.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Burger implements Serializable {
    @NotNull
    @Size(min = 5, message = "must at least be 5 letters")
    private String name;
    @NotNull
    @Size(min = 2, message = "choose at least 2 ingredients")
    private List<String> ingredients = new ArrayList<>();
}
