package cybersoft.java11.group8.pizza_store.warehouse.service;

import java.util.List;
import java.util.Optional;

import cybersoft.java11.group8.pizza_store.common_data.GenericService;
import cybersoft.java11.group8.pizza_store.warehouse.dto.CreateRawMaterialDto;
import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;

public interface RawMaterialService extends GenericService<RawMaterial, Long> {

	RawMaterial save(CreateRawMaterialDto dto);

	Optional<RawMaterial> findRawMaterialByName(String name);
	
	List<RawMaterial> findRawMaterialBySupplierName(String name);

	RawMaterial updateRawMaterialInfo(CreateRawMaterialDto dto, Long rawMaterialId);
	
	Boolean existById(Long rawMaterialId);
}
