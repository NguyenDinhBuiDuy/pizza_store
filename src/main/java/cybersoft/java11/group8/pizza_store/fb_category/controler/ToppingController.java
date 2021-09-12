package cybersoft.java11.group8.pizza_store.fb_category.controler;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.java11.group8.pizza_store.common_data.model.ResponseHandler;
import cybersoft.java11.group8.pizza_store.fb_category.dto.CreatePizzaDTO;
import cybersoft.java11.group8.pizza_store.fb_category.dto.CreatePizzaToppingDTO;
import cybersoft.java11.group8.pizza_store.fb_category.model.pizza.Pizza;
import cybersoft.java11.group8.pizza_store.fb_category.model.pizza.PizzaTopping;
import cybersoft.java11.group8.pizza_store.fb_category.service.ToppingService;
import io.swagger.models.Response;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping ("/api/topping")
public class ToppingController {
	
	private ToppingService _ToppingService;
	
	@Autowired
	public ToppingController(ToppingService _ToppingService) {
		super();
		this._ToppingService = _ToppingService;
	}
	
	
	@GetMapping("")
	public ResponseEntity<Object> findAllTopping(){
		List<PizzaTopping> toppings = _ToppingService.findAll();
		if (toppings.isEmpty()) {
			return ResponseHandler.getResponse("there is no data", HttpStatus.BAD_REQUEST);
		}
		return ResponseHandler.getResponse(toppings, HttpStatus.OK);
	}
	
	@GetMapping("/{topping-name}")
	public ResponseEntity<Object> findPizzaToppingByName(@PathVariable("topping-name") String toppingName){
		Optional<PizzaTopping> topping = _ToppingService.findToppingByName(toppingName);
		if (topping.isEmpty()) {
			return ResponseHandler.getResponse("there is no data", HttpStatus.BAD_REQUEST);
		}
		return ResponseHandler.getResponse(topping, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> savePizzaTopping(@Valid @RequestBody CreatePizzaToppingDTO dto, 
			BindingResult errors){
		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}
		PizzaTopping topping  = new PizzaTopping();
		topping = _ToppingService.save(dto);
		return ResponseHandler.getResponse(topping, HttpStatus.CREATED);
	}


	
	

}
