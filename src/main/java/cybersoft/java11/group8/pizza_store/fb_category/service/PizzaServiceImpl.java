package cybersoft.java11.group8.pizza_store.fb_category.service;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import cybersoft.java11.group8.pizza_store.common_data.GenericServiceImpl;
import cybersoft.java11.group8.pizza_store.fb_category.dto.CreatePizzaDTO;
import cybersoft.java11.group8.pizza_store.fb_category.dto.UpdatePizzaDTO;
import cybersoft.java11.group8.pizza_store.fb_category.model.FB_Status;
import cybersoft.java11.group8.pizza_store.fb_category.model.FB_Type;
import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.Beverage;
import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.DrinkType;
import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.SugarPercent;
import cybersoft.java11.group8.pizza_store.fb_category.model.pizza.Pizza;
import cybersoft.java11.group8.pizza_store.fb_category.model.pizza.PizzaTopping;
import cybersoft.java11.group8.pizza_store.fb_category.repository.PizzaRepository;
import cybersoft.java11.group8.pizza_store.fb_category.repository.ToppingRepository;
import cybersoft.java11.group8.pizza_store.util.MapDTOToModel;
import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;
import cybersoft.java11.group8.pizza_store.warehouse.repository.RawMaterialRepository;
import lombok.AllArgsConstructor;

@Service
public class PizzaServiceImpl extends GenericServiceImpl<Pizza, Long> implements PizzaService {
	
	private PizzaRepository _pizzaRepository;
	private RawMaterialRepository _rawMaterialRepository;
	private ToppingRepository _toppingRepository;
	private MapDTOToModel mapper;
	
	@Autowired
	public PizzaServiceImpl(JpaRepository<Pizza, Long> repository, PizzaRepository _pizzaRepository,
			RawMaterialRepository _rawMaterialRepository, ToppingRepository _toppingRepository, MapDTOToModel mapper) {
		super(repository);
		this._pizzaRepository = _pizzaRepository;
		this._rawMaterialRepository = _rawMaterialRepository;
		this._toppingRepository = _toppingRepository;
		this.mapper = mapper;
	}
	private static final Logger log = LoggerFactory.getLogger(PizzaServiceImpl.class);

	@Override
	public Pizza save(CreatePizzaDTO dto) {
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

		Optional<RawMaterial> rawMaterial = _rawMaterialRepository.findByName(rawMaterialName);

		pizza.addRawMaterial(rawMaterial.get());
		return _pizzaRepository.save(pizza);
	}

	@Override
	public boolean removeRawMeterialInPizza(String rawMaterialName, @Valid @NotNull Long pizzaId) {
		Pizza pizza = _pizzaRepository.getOne(pizzaId);

		Optional<RawMaterial> rawMaterial = _rawMaterialRepository.findByName(rawMaterialName);
		
		boolean result = pizza.removeRawMaterial(rawMaterial.get());
		if (rawMaterial.isPresent() && result) {
			_pizzaRepository.save(pizza);
			return true;
		}

		return false;
	}

	@Override
	public Pizza update(UpdatePizzaDTO dto, Long pizzaId) {
		Pizza model = _pizzaRepository.getOne(pizzaId);
		model = (Pizza) mapper.map(dto, model);

		return _pizzaRepository.save(model);
	}

	@Override
	public Pizza addTopping(@Valid String toppingName, Long pizzaId) {
		Pizza pizza = _pizzaRepository.getOne(pizzaId);

		Optional<PizzaTopping> topping = _toppingRepository.findPizzaToppingByName(toppingName);

		pizza.addTopping(topping.get());

		return _pizzaRepository.save(pizza);
	}

	@Override
	public boolean removeToppingPizza(String toppingName, Long pizzaId) {
		Pizza pizza = _pizzaRepository.getOne(pizzaId);

		Optional<PizzaTopping> topping = _toppingRepository.findByName(toppingName);

		boolean result = pizza.removeTopping(topping.get());
		if (topping.isPresent() && result) {
			_pizzaRepository.save(pizza);
			return true;
		}

		return false;

	}

	@Override
	public Optional<Pizza> findByCode(String code) {
		return _pizzaRepository.findByCode(code);
	}
}
