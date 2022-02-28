package at.kaindorf.webshoprest.bl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "WebshopUser")
public class User {
    @Id
    private String mail;
    private String password;
    private String name;
}
