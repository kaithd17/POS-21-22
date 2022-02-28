package at.kaindorf.webshoprest.database;

import at.kaindorf.webshoprest.bl.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}