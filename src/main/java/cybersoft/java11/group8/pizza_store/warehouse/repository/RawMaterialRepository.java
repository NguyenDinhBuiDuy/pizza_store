package cybersoft.java11.group8.pizza_store.warehouse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;

@Repository
public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {

	Optional<RawMaterial> findByName(String name);
	
	List<RawMaterial> findRawMaterialBySupplierName(String name);
}
