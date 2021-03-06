package cybersoft.java11.group8.pizza_store.warehouse.service;

import cybersoft.java11.group8.pizza_store.common_data.GenericService;
import cybersoft.java11.group8.pizza_store.warehouse.dto.CreateSupplierDto;
import cybersoft.java11.group8.pizza_store.warehouse.model.Supplier;

public interface SupplierService extends GenericService<Supplier, Long> {
	
	Supplier save(CreateSupplierDto dto);

	Supplier findSupplierByName(String name);

	Supplier updateSupplierInfo(CreateSupplierDto dto, Long supplierId);
}
