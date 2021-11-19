package at.kaindorf.springburger.repo;

import at.kaindorf.springburger.pojos.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {
}