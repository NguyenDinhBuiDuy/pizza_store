package cybersoft.java11.group8.pizza_store.fb_category.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import cybersoft.java11.group8.pizza_store.common_data.GenericServiceImpl;
import cybersoft.java11.group8.pizza_store.fb_category.dto.CreatePizzaToppingDTO;
import cybersoft.java11.group8.pizza_store.fb_category.model.pizza.PizzaTopping;
import cybersoft.java11.group8.pizza_store.fb_category.repository.ToppingRepository;
import cybersoft.java11.group8.pizza_store.util.MapDTOToModel;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ToppingServiceImpl extends GenericServiceImpl<PizzaTopping, Long> implements ToppingService {

	ToppingRepository _ToppingRepository;
	MapDTOToModel mapper;

	@Override
	public Optional<PizzaTopping> findToppingByName(String toppingName) {
		return _ToppingRepository.findByName(toppingName);
	}

	@Override
	public PizzaTopping save(@Valid CreatePizzaToppingDTO dto) {
		PizzaTopping model = new PizzaTopping();
		model = (PizzaTopping) mapper.map(dto, model);
		
		return _ToppingRepository.save(model);
	}

}
