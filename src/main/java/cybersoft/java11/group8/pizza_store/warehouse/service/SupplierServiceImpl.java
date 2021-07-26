package cybersoft.java11.group8.pizza_store.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java11.group8.pizza_store.warehouse.dto.CreateSupplierDto;
import cybersoft.java11.group8.pizza_store.warehouse.model.Supplier;
import cybersoft.java11.group8.pizza_store.warehouse.repository.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService {
	@Autowired
	private SupplierRepository supplierRepository;

	@Override
	public Supplier save(CreateSupplierDto dto) {
		Supplier supplier = new Supplier();
		supplier.name(dto.getName())
				.address(dto.getAddress())
				.email(dto.getEmail())
				.phone(dto.getPhone());
		return supplierRepository.save(supplier);
	}
}
