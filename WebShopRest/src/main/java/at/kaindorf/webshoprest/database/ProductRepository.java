package at.kaindorf.webshoprest.database;

import at.kaindorf.webshoprest.bl.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}