package cybersoft.java11.group8.pizza_store.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cybersoft.java11.group8.pizza_store.warehouse.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	Supplier findSuplierByName(String name);
	
}
