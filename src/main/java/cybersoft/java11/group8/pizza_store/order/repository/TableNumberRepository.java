package cybersoft.java11.group8.pizza_store.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cybersoft.java11.group8.pizza_store.order.model.TableNumber;

public interface TableNumberRepository extends JpaRepository<TableNumber, Long>{
	
	Optional<TableNumber> findByTableNumber(Integer tableNumber);
}
