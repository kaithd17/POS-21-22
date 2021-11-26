package at.kaindorf.springburger.repo;

import at.kaindorf.springburger.pojos.Burger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BurgerRepository extends JpaRepository<Burger, Long> {

    @Query("SELECT b FROM Burger b WHERE b.id = :name ORDER BY b.id")
    List<Burger> findNameOrderById(@Param("name") String name);

    @Query("SELECT DISTINCT b FROM Burger b WHERE b.name = :name")
    List<Burger> findDistinctByName(@Param("name") String name);
}
