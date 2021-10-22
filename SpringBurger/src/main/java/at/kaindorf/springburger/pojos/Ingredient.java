package at.kaindorf.springburger.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient implements Serializable {
    private String id;
    private String name;
    private Type type;

    public static enum Type {
        PATTY, VEGGIE, CHEESE;
    }
}
