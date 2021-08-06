package cybersoft.java11.group8.pizza_store.fb_category.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cybersoft.java11.group8.pizza_store.fb_category.model.pizza.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long>{

	Optional<Pizza> findByCode(String code);

	Optional<Pizza> findByName(String name);

}
