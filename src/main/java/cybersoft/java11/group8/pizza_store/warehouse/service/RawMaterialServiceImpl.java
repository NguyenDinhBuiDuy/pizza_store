package cybersoft.java11.group8.pizza_store.warehouse.service;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import cybersoft.java11.group8.pizza_store.common_data.GenericServiceImpl;
import cybersoft.java11.group8.pizza_store.util.MapDTOToModel;
import cybersoft.java11.group8.pizza_store.warehouse.dto.CreateRawMaterialDto;
import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;
import cybersoft.java11.group8.pizza_store.warehouse.model.Supplier;
import cybersoft.java11.group8.pizza_store.warehouse.repository.RawMaterialRepository;
import cybersoft.java11.group8.pizza_store.warehouse.repository.SupplierRepository;
import lombok.AllArgsConstructor;

@Service
public class RawMaterialServiceImpl extends GenericServiceImpl<RawMaterial, Long> implements RawMaterialService {

	private RawMaterialRepository rawMaterialRepository;
	private SupplierRepository supplierRepository;
	private MapDTOToModel<Object, RawMaterial> mapper;
	
	@Autowired
	public RawMaterialServiceImpl(JpaRepository<RawMaterial, Long> repository,
			RawMaterialRepository rawMaterialRepository, SupplierRepository supplierRepository,
			MapDTOToModel<Object, RawMaterial> mapper) {
		super(repository);
		this.rawMaterialRepository = rawMaterialRepository;
		this.supplierRepository = supplierRepository;
		this.mapper = mapper;
	}

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
	public Optional<RawMaterial> findRawMaterialByName(String name) {
		return rawMaterialRepository.findByName(name);
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

	@Override
	public boolean ExistRawMaterialId(@Valid @NotNull Long rawMaterialId) {
		return rawMaterialRepository.existsById(rawMaterialId);
	}
}
