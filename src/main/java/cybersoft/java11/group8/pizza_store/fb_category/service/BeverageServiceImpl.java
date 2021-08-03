package cybersoft.java11.group8.pizza_store.fb_category.service;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java11.group8.pizza_store.common_data.GenericServiceImpl;
import cybersoft.java11.group8.pizza_store.fb_category.dto.CreateBeverageDTO;
import cybersoft.java11.group8.pizza_store.fb_category.model.FB_Status;
import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.Beverage;
import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.DrinkType;
import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.SugarPercent;
import cybersoft.java11.group8.pizza_store.fb_category.repository.BeverageRepository;
import cybersoft.java11.group8.pizza_store.util.MapDTOToModel;
import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;
import cybersoft.java11.group8.pizza_store.warehouse.repository.RawMaterialRepository;

@Service
public class BeverageServiceImpl extends GenericServiceImpl<Beverage, Long> implements BeverageService {

	@Autowired
	BeverageRepository _beverageRepository;

	@Autowired
	RawMaterialRepository _rawMaterialRepository;

	@Autowired
	MapDTOToModel mapper;

	@Override
	public Beverage save(@Valid CreateBeverageDTO dto) {

		Beverage model = new Beverage();
		model = (Beverage) mapper.map(dto, model);

		// set default Status, sugar percent, drink type to load for the beverage order
		// by default
		model.setStatus(FB_Status.IN_STOCK);
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

		RawMaterial rawMaterial = _rawMaterialRepository.findRawMaterialByName(rawMaterialName);

		return beverage.addRawMaterial(rawMaterial);
	}

	@Override
	public boolean removeRawMeterialInBeverage(String rawMaterialName, @Valid @NotNull Long beverageId) {
		Beverage beverage = _beverageRepository.getOne(beverageId);
		
		Set<RawMaterial> recepies = beverage.getRecipes();
		
		for (RawMaterial rawMaterial : recepies) {
			 if (rawMaterial.getName().equals(rawMaterialName)) {
				 recepies.remove(rawMaterial);
				 beverage.setRecipes(recepies);
				 
				 return true;
			 }
		}
		return false;

	}

}
