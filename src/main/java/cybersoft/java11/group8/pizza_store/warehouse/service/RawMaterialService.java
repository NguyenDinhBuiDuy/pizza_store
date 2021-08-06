package cybersoft.java11.group8.pizza_store.warehouse.service;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import cybersoft.java11.group8.pizza_store.common_data.GenericService;
import cybersoft.java11.group8.pizza_store.warehouse.dto.CreateRawMaterialDto;
import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;

public interface RawMaterialService extends GenericService<RawMaterial, Long> {

	RawMaterial save(@Valid CreateRawMaterialDto dto);

	Optional<RawMaterial> findRawMaterialByName(String name);

	RawMaterial updateRawMaterialInfo(@Valid CreateRawMaterialDto dto, Long rawMaterialId);

	boolean ExistRawMaterialId(@Valid @NotNull Long rawMaterialId);
	
}
