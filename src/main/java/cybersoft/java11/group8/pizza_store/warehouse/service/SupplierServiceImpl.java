package cybersoft.java11.group8.pizza_store.warehouse.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import cybersoft.java11.group8.pizza_store.common_data.GenericServiceImpl;
import cybersoft.java11.group8.pizza_store.util.MapDTOToModel;
import cybersoft.java11.group8.pizza_store.warehouse.dto.CreateSupplierDto;
import cybersoft.java11.group8.pizza_store.warehouse.model.Supplier;
import cybersoft.java11.group8.pizza_store.warehouse.repository.SupplierRepository;
import lombok.AllArgsConstructor;

@Service
public class SupplierServiceImpl extends GenericServiceImpl<Supplier, Long> implements SupplierService {

	private SupplierRepository supplierRepository;
	private MapDTOToModel<Object, Supplier> mapper;
	
	@Autowired
	public SupplierServiceImpl(JpaRepository<Supplier, Long> repository, SupplierRepository supplierRepository,
			MapDTOToModel<Object, Supplier> mapper) {
		super(repository);
		this.supplierRepository = supplierRepository;
		this.mapper = mapper;
	}

	@Override
	public Supplier save(CreateSupplierDto dto) {
		Supplier supplier = new Supplier();
		supplier = mapper.map(dto, supplier);
		return supplierRepository.save(supplier);
	}

	@Override
	public Optional<Supplier> findSupplierByName(String name) {
		return supplierRepository.findByName(name);
	}

	@Override
	public Supplier updateSupplierInfo(CreateSupplierDto dto, Long supplierId) {
		Supplier supplier = supplierRepository.getOne(supplierId);
		supplier = mapper.map(dto, supplier);
		return supplierRepository.save(supplier);
	}

	@Override
	public Boolean existById(Long supplierId) {
		return supplierRepository.existsById(supplierId);
	}
}
