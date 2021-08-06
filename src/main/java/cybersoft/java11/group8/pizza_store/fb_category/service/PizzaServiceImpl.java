package cybersoft.java11.group8.pizza_store.fb_category.service;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java11.group8.pizza_store.common_data.GenericServiceImpl;
import cybersoft.java11.group8.pizza_store.fb_category.dto.CreatePizzaDTO;
import cybersoft.java11.group8.pizza_store.fb_category.model.FB_Status;
import cybersoft.java11.group8.pizza_store.fb_category.model.FB_Type;
import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.Beverage;
import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.DrinkType;
import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.SugarPercent;
import cybersoft.java11.group8.pizza_store.fb_category.model.pizza.Pizza;
import cybersoft.java11.group8.pizza_store.fb_category.repository.PizzaRepository;
import cybersoft.java11.group8.pizza_store.util.MapDTOToModel;
import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;
import cybersoft.java11.group8.pizza_store.warehouse.repository.RawMaterialRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PizzaServiceImpl extends GenericServiceImpl<Pizza, Long> implements PizzaService{

	private PizzaRepository _pizzaRepository;
	private RawMaterialRepository _rawMaterialRepository;
	private MapDTOToModel mapper;
	
	@Override
	public Pizza save( CreatePizzaDTO dto) {
		Pizza model = new Pizza();
		model = (Pizza) mapper.map(dto, model);
		model.setStatus(FB_Status.IN_STOCK);
		model.setType(FB_Type.FOOD);
		
		return _pizzaRepository.save(model);
	}

	@Override
	public boolean existPizza(@Valid @NotNull Long pizzaId) {
		return _pizzaRepository.existsById(pizzaId);
	}

	@Override
	public Pizza addRawMaterial(@Valid String rawMaterialName, Long pizzaId) {
		Pizza pizza = _pizzaRepository.getOne(pizzaId);

		RawMaterial rawMaterial = _rawMaterialRepository.findByName(rawMaterialName).get();

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

	@Override
	public Pizza update( CreatePizzaDTO dto, Long pizzaId) {
		Pizza model = _pizzaRepository.getOne(pizzaId);
		model = (Pizza) mapper.map(dto, model);
		return _pizzaRepository.save(model);
	}
}
