package cybersoft.java11.group8.pizza_store.fb_category.service;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java11.group8.pizza_store.common_data.GenericServiceImpl;
import cybersoft.java11.group8.pizza_store.fb_category.dto.CreateBeverageDTO;
import cybersoft.java11.group8.pizza_store.fb_category.dto.UpdateBeverageDTO;
import cybersoft.java11.group8.pizza_store.fb_category.model.FB_Status;
import cybersoft.java11.group8.pizza_store.fb_category.model.FB_Type;
import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.Beverage;
import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.DrinkType;
import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.SugarPercent;
import cybersoft.java11.group8.pizza_store.fb_category.repository.BeverageRepository;
import cybersoft.java11.group8.pizza_store.util.MapDTOToModel;
import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;
import cybersoft.java11.group8.pizza_store.warehouse.repository.RawMaterialRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BeverageServiceImpl extends GenericServiceImpl<Beverage, Long> implements BeverageService {

	private BeverageRepository _beverageRepository;

	private RawMaterialRepository _rawMaterialRepository;
	
	private MapDTOToModel mapper;

	@Override
	public Beverage save(@Valid CreateBeverageDTO dto) {

		Beverage model = new Beverage();
		model = (Beverage) mapper.map(dto, model);

		// set default Status, sugar percent, drink type to load for the beverage order
		// by default
		model.setStatus(FB_Status.IN_STOCK);
		model.setType(FB_Type.BEVERAGE);
		model.setSugarPercent(SugarPercent.SWEET);
		model.setDrinkType(DrinkType.DINE_IN);

		return _beverageRepository.save(model);
	}

	@Override
	public boolean existBeverage(@Valid @NotNull Long beverageId) {
		return _beverageRepository.existsById(beverageId);
	}

	@Override
	public Beverage addRawMaterial(@Valid String rawMaterialName, @Valid @NotNull Long beverageId) {
		Beverage beverage = _beverageRepository.getOne(beverageId);

		RawMaterial rawMaterial = _rawMaterialRepository.findByName(rawMaterialName).get();

		beverage.addRawMaterial(rawMaterial);
		
		return _beverageRepository.save(beverage);
	}

	@Override
	public boolean removeRawMeterialInBeverage(String rawMaterialName, @Valid @NotNull Long beverageId) {
		Beverage beverage = _beverageRepository.getOne(beverageId);
		
		Optional<RawMaterial> rawMaterial = _rawMaterialRepository.findByName(rawMaterialName);
		
		boolean result = beverage.removeRawMaterial(rawMaterial.get());
		
		if (rawMaterial.isPresent() && result) {
			_beverageRepository.save(beverage);
			return true;
		}
		return false;
	}

	@Override
	public Beverage update(@Valid UpdateBeverageDTO dto, Long beverageId) {
		Beverage model = _beverageRepository.getOne(beverageId);
		model = (Beverage) mapper.map(dto, model);
		
		return _beverageRepository.save(model);
	}

	@Override
	public Optional<Beverage> findByCode(String code) {
		return _beverageRepository.findByCode(code);
	}

}
