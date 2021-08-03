package cybersoft.java11.group8.pizza_store.fb_category.controler;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.java11.group8.pizza_store.common_data.model.ResponseHandler;
import cybersoft.java11.group8.pizza_store.fb_category.dto.CreateBeverageDTO;
import cybersoft.java11.group8.pizza_store.fb_category.dto.CreatePizzaDTO;
import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.Beverage;
import cybersoft.java11.group8.pizza_store.fb_category.model.pizza.Pizza;
import cybersoft.java11.group8.pizza_store.fb_category.service.BeverageService;
import cybersoft.java11.group8.pizza_store.fb_category.service.PizzaService;

@RestController
@RequestMapping("/api/pizza")

public class PizzaController {
	@Autowired 
	PizzaService _pizzaService;
	
	@GetMapping("")
	public ResponseEntity<Object> findAllPizza(){
		List<Pizza> pizzas = _pizzaService.findAll();
		if (pizzas.isEmpty()) {
			return ResponseHandler.getResponse("there is no data", HttpStatus.BAD_REQUEST);
		}
		return ResponseHandler.getResponse(pizzas, HttpStatus.OK);
	}
	@GetMapping("{pizza-id}")
	public ResponseEntity<Object> findPizzaById(@Valid @PathVariable("pizza-id") Long pizzaId, BindingResult errors){
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Optional<Pizza> pizza = _pizzaService.findById(pizzaId);
		if (pizza.isEmpty()) {
			return ResponseHandler.getResponse( "there is no data", HttpStatus.BAD_REQUEST);
		}
		
		return ResponseHandler.getResponse(pizza, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> savePizza(@Valid @RequestBody CreatePizzaDTO dto, BindingResult errors){
		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}
		Pizza pizza  = new Pizza();
		pizza = _pizzaService.save(dto);
		return ResponseHandler.getResponse(pizza, HttpStatus.CREATED);
	}
	
	@PutMapping("/{pizza-id}")
	public ResponseEntity<Object> updateBeverage(@Valid CreatePizzaDTO dto, @Valid @NotNull @PathVariable ("pizza-id") Long pizzaId, BindingResult errors){
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		if (!_pizzaService.existPizza(pizzaId))
			return ResponseHandler.getResponse("there is no Pizza id: " + pizzaId, HttpStatus.BAD_REQUEST);
		
		Pizza updatePizza  = new Pizza();
		updatePizza = _pizzaService.save(dto);
		return ResponseHandler.getResponse(updatePizza, HttpStatus.CREATED);
	}
	
	@PutMapping("/{pizza-id}/raw_material")
	public ResponseEntity<Object> addRawMaterialToBeverage(@Valid String rawMaterialName, @Valid @NotNull @PathVariable ("pizza-id") Long pizzaId, BindingResult errors){
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		if (!_pizzaService.existPizza(pizzaId))
			return ResponseHandler.getResponse("there is no pizza id: " + pizzaId, HttpStatus.BAD_REQUEST);
		
		Pizza updatePizza  = new Pizza();
		updatePizza = _pizzaService.addRawMaterial(rawMaterialName,pizzaId);
		return ResponseHandler.getResponse(updatePizza, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{pizza-id}")
	public ResponseEntity<Object> deleteBeverage(@Valid @NotNull @PathVariable ("pizza-id") Long pizzaId, BindingResult errors){
		
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		if (!_pizzaService.existPizza(pizzaId))
			return ResponseHandler.getResponse("there is no pizza id: " + pizzaId, HttpStatus.BAD_REQUEST);
		
		_pizzaService.deleteById(pizzaId);
		return ResponseHandler.getResponse("delete successfull", HttpStatus.OK);
	}
	
	@DeleteMapping("/{pizza-id}/raw_material")
	public ResponseEntity<Object> deleteRawMaterialInBeverage(@RequestBody String RawMaterialName ,@Valid @NotNull @PathVariable ("pizza-id") Long pizzaId, BindingResult errors){
		
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		if (!_pizzaService.existPizza(pizzaId))
			return ResponseHandler.getResponse("there is no pizza id: " + pizzaId, HttpStatus.BAD_REQUEST);
		
		boolean result = _pizzaService.removeRawMeterialInPizza(RawMaterialName, pizzaId);
		
		if (!result) 
			return ResponseHandler.getResponse("there is no RawMaterial: " + RawMaterialName, HttpStatus.BAD_REQUEST);
	
		return ResponseHandler.getResponse("remove raw material: " + RawMaterialName + "successfull", HttpStatus.OK);
	}
	
}