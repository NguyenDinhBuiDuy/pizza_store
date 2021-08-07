package cybersoft.java11.group8.pizza_store.fb_category.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import cybersoft.java11.group8.pizza_store.common_data.GenericService;
import cybersoft.java11.group8.pizza_store.fb_category.dto.CreatePizzaDTO;
import cybersoft.java11.group8.pizza_store.fb_category.model.pizza.Pizza;

public interface PizzaService extends GenericService<Pizza, Long> {

	Pizza save(@Valid CreatePizzaDTO dto);

	boolean existPizza(@Valid @NotNull Long pizzaId);

	Pizza addRawMaterial(@Valid String rawMaterialName, @Valid @NotNull Long pizzaId);

	boolean removeRawMeterialInPizza(String rawMaterialName, @Valid @NotNull Long pizzaId);

	Pizza update(@Valid CreatePizzaDTO dto, Long pizzaId);

	Pizza addTopping(@Valid String toppingName, Long pizzaId);

}
