package cybersoft.java11.group8.pizza_store.warehouse.service;

import cybersoft.java11.group8.pizza_store.common_data.GenericService;
import cybersoft.java11.group8.pizza_store.warehouse.dto.CreateRawMaterialDto;
import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;

public interface RawMaterialService extends GenericService<RawMaterial, Long> {

	RawMaterial save(CreateRawMaterialDto dto);

	RawMaterial findRawMaterialByName(String name);

	RawMaterial updateRawMaterialInfo(CreateRawMaterialDto dto, Long rawMaterialId);
	
	Boolean existById(Long rawMaterialId);
}
