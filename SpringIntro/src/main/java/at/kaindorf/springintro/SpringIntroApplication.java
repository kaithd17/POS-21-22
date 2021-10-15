package at.kaindorf.springintro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringIntroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIntroApplication.class, args);
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(name = "name", defaultValue = "Hello World") String name){
        return String.format("Hello %s", name);
    }
}
