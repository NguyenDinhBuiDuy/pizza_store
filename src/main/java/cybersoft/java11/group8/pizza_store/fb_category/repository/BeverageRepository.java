package cybersoft.java11.group8.pizza_store.fb_category.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.Beverage;

@Repository
public interface BeverageRepository extends JpaRepository<Beverage, Long> {

//	Optional<Beverage> findByFbCode(String code);
//
//	Optional<Beverage> findByFbName(String name);

}
