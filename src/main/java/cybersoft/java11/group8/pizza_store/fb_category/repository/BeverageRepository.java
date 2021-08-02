package cybersoft.java11.group8.pizza_store.fb_category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.Beverage;

public interface BeverageRepository extends JpaRepository<Beverage, Long> {

}
