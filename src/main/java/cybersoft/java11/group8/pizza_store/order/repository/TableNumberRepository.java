package cybersoft.java11.group8.pizza_store.order.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import cybersoft.java11.group8.pizza_store.order.model.TableNumber;

public interface TableNumberRepository extends JpaRepository<TableNumber, Long>{
	
	Set<TableNumber> findByTableNumber(Integer tableNumber);
}
