package cybersoft.java11.group8.pizza_store.fb_category.repository;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cybersoft.java11.group8.pizza_store.fb_category.model.pizza.PizzaTopping;

@Repository
public interface ToppingRepository extends JpaRepository<PizzaTopping, Long>{

	Optional<PizzaTopping> findByName(String toppingName);

	Optional<PizzaTopping> findPizzaToppingByName(String toppingName);

}
