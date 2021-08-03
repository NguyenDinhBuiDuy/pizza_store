package cybersoft.java11.group8.pizza_store.fb_category.service;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java11.group8.pizza_store.common_data.GenericServiceImpl;
import cybersoft.java11.group8.pizza_store.fb_category.dto.CreatePizzaDTO;
import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.Beverage;
import cybersoft.java11.group8.pizza_store.fb_category.model.pizza.Pizza;
import cybersoft.java11.group8.pizza_store.fb_category.repository.PizzaRepository;
import cybersoft.java11.group8.pizza_store.util.MapDTOToModel;
import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;
import cybersoft.java11.group8.pizza_store.warehouse.repository.RawMaterialRepository;

@Service
public class PizzaServiceImpl extends GenericServiceImpl<Pizza, Long> implements PizzaService{

	@Autowired
	PizzaRepository _pizzaRepository;
	RawMaterialRepository _rawMaterialRepository;
	
	@Autowired
	MapDTOToModel mapper;
	
	@Override
	public Pizza save(@Valid CreatePizzaDTO dto) {
		Pizza model = new Pizza();
		model = (Pizza) mapper.map(dto, model);
		
		return _pizzaRepository.save(model);
	}

	@Override
	public boolean existPizza(@Valid @NotNull Long pizzaId) {
		return _pizzaRepository.existsById(pizzaId);
	}

	@Override
	public Pizza addRawMaterial(@Valid String rawMaterialName, @Valid @NotNull Long pizzaId) {
		Pizza pizza = _pizzaRepository.getOne(pizzaId);

		RawMaterial rawMaterial = _rawMaterialRepository.findRawMaterialByName(rawMaterialName);

		return pizza.addRawMaterial(rawMaterial);
	}

	@Override
	public boolean removeRawMeterialInPizza(String rawMaterialName, @Valid @NotNull Long pizzaId) {
	Pizza pizza = _pizzaRepository.getOne(pizzaId);
		
		Set<RawMaterial> recepies = pizza.getRecipes();
		
		for (RawMaterial rawMaterial : recepies) {
			 if (rawMaterial.getName().equals(rawMaterialName)) {
				 recepies.remove(rawMaterial);
				 pizza.setRecipes(recepies);
				 
				 return true;
			 }
		}
		return false;

	}
}
