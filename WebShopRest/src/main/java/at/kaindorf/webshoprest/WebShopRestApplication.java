package at.kaindorf.webshoprest;

import at.kaindorf.webshoprest.bl.Product;
import at.kaindorf.webshoprest.bl.User;
import at.kaindorf.webshoprest.database.ProductRepository;
import at.kaindorf.webshoprest.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class WebShopRestApplication {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(WebShopRestApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        Product product = new Product(0L, "Harry Potter und der Stein der Weisen", "Ein gutes Buch", 15.90f, null);
        Product product2 = new Product(1L, "Harry Potter und die Kammer des Schreckens", "Ein gutes Buch", 15.90f, null);
        Product product3 = new Product(2L, "Harry Potter und der Gefangende von Askaban", "Ein gutes Buch", 15.90f, null);

        productRepository.save(product);
        productRepository.save(product2);
        productRepository.save(product3);

        userRepository.save(new User("test@mail.com", "admin", "test"));
    }
}
