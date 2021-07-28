package cybersoft.java11.group8.pizza_store.warehouse.service;

import cybersoft.java11.group8.pizza_store.warehouse.dto.CreateSupplierDto;
import cybersoft.java11.group8.pizza_store.warehouse.model.Supplier;

public interface SupplierService {
	
	Supplier save(CreateSupplierDto dto);
}
