package at.kaindorf.intro.json;

import at.kaindorf.intro.pojos.Customer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JsonTester {

    public static void main(String[] args) {
        Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "customer.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Customer> customers = mapper.readValue(path.toFile(), new TypeReference<List<Customer>>() { });
            customers.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
