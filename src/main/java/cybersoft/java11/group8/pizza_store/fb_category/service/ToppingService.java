package cybersoft.java11.group8.pizza_store.fb_category.service;

import java.util.Optional;

import javax.validation.Valid;

import cybersoft.java11.group8.pizza_store.common_data.GenericService;
import cybersoft.java11.group8.pizza_store.fb_category.dto.CreatePizzaToppingDTO;
import cybersoft.java11.group8.pizza_store.fb_category.model.pizza.PizzaTopping;

public interface ToppingService extends GenericService<PizzaTopping, Long>{

	Optional<PizzaTopping> findToppingByName(String toppingName);

	PizzaTopping save(@Valid CreatePizzaToppingDTO dto);

}
