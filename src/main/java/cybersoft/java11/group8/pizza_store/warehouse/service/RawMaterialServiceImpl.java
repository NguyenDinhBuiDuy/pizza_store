package cybersoft.java11.group8.pizza_store.warehouse.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java11.group8.pizza_store.common_data.GenericServiceImpl;
import cybersoft.java11.group8.pizza_store.util.MapDTOToModel;
import cybersoft.java11.group8.pizza_store.warehouse.dto.CreateRawMaterialDto;
import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;
import cybersoft.java11.group8.pizza_store.warehouse.model.Supplier;
import cybersoft.java11.group8.pizza_store.warehouse.repository.RawMaterialRepository;
import cybersoft.java11.group8.pizza_store.warehouse.repository.SupplierRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RawMaterialServiceImpl extends GenericServiceImpl<RawMaterial, Long> implements RawMaterialService {
	@Autowired
	private RawMaterialRepository rawMaterialRepository;
	private SupplierRepository supplierRepository;
	private MapDTOToModel<Object, RawMaterial> mapper;

	@Override
	public RawMaterial save(CreateRawMaterialDto dto) {
		RawMaterial rawMaterial = new RawMaterial();
		rawMaterial = mapper.map(dto, rawMaterial);
		Optional<Supplier> supplierOpt = supplierRepository.findByName(dto.getSupplier());
		if(supplierOpt.isPresent())
			rawMaterial.setSupplier(supplierOpt.get());
		return rawMaterialRepository.save(rawMaterial);
	}

	@Override
	public RawMaterial findRawMaterialByName(String name) {
		return rawMaterialRepository.findRawMaterialByName(name);
	}

	@Override
	public RawMaterial updateRawMaterialInfo(CreateRawMaterialDto dto, Long rawMaterialId) {
		RawMaterial rawMaterial = rawMaterialRepository.getOne(rawMaterialId);
		rawMaterial = mapper.map(dto, rawMaterial);
		Optional<Supplier> supplierOpt = supplierRepository.findByName(dto.getSupplier());
		if(supplierOpt.isPresent())
			rawMaterial.setSupplier(supplierOpt.get());
		return rawMaterialRepository.save(rawMaterial);
	}
}