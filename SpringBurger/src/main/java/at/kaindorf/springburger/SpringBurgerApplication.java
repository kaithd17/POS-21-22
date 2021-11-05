package at.kaindorf.springburger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Component scan startet von hier
@SpringBootApplication
public class SpringBurgerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBurgerApplication.class, args);
    }

}
