package at.kaindorf.webshoprest.controller;

import at.kaindorf.webshoprest.bl.Product;
import at.kaindorf.webshoprest.database.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct (@PathVariable("id") Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return ResponseEntity.of(productOptional);
    }
}
