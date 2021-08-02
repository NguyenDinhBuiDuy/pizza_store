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
import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.Beverage;
import cybersoft.java11.group8.pizza_store.fb_category.service.BeverageService;
import cybersoft.java11.group8.pizza_store.user.dto.CreateUserDTO;
import cybersoft.java11.group8.pizza_store.user.model.User;
import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;

@RestController
@RequestMapping("/api/beverage")
public class BeverageCategoryController {

	@Autowired 
	BeverageService _beverageService;
	
	@GetMapping("")
	public ResponseEntity<Object> findAllBeverage(){
		List<Beverage> beverages = _beverageService.findAll();
		if (beverages.isEmpty()) {
			return ResponseHandler.getResponse("there is no data", HttpStatus.BAD_REQUEST);
		}
		return ResponseHandler.getResponse(beverages, HttpStatus.OK);
	}
	@GetMapping("{beverage-id}")
	public ResponseEntity<Object> findBeverageById(@Valid @PathVariable("beverage-id") Long beverageId, BindingResult errors){
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		Optional<Beverage> beverages = _beverageService.findById(beverageId);
		if (beverages.isEmpty()) {
			return ResponseHandler.getResponse( "there is no data", HttpStatus.BAD_REQUEST);
		}
		
		return ResponseHandler.getResponse(beverages, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> saveBeverage(@Valid @RequestBody CreateBeverageDTO dto, BindingResult errors){
		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}
		Beverage beverage  = new Beverage();
		beverage = _beverageService.save(dto);
		return ResponseHandler.getResponse(beverage, HttpStatus.CREATED);
	}
	
	@PutMapping("/{beverage-id}")
	public ResponseEntity<Object> updateBeverage(@Valid CreateBeverageDTO dto, @Valid @NotNull @PathVariable ("beverage-id") Long beverageId, BindingResult errors){
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		if (!_beverageService.existBeverage(beverageId))
			return ResponseHandler.getResponse("there is no beverage id: " + beverageId, HttpStatus.BAD_REQUEST);
		
		Beverage updateBeverage  = new Beverage();
		updateBeverage = _beverageService.save(dto);
		return ResponseHandler.getResponse(updateBeverage, HttpStatus.CREATED);
	}
	
	@PutMapping("/{beverage-id}/raw_material")
	public ResponseEntity<Object> addRawMaterialToBeverage(@Valid String rawMaterialName, @Valid @NotNull @PathVariable ("beverage-id") Long beverageId, BindingResult errors){
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		if (!_beverageService.existBeverage(beverageId))
			return ResponseHandler.getResponse("there is no beverage id: " + beverageId, HttpStatus.BAD_REQUEST);
		
		Beverage updateBeverage  = new Beverage();
		updateBeverage = _beverageService.addRawMaterial(rawMaterialName,beverageId);
		return ResponseHandler.getResponse(updateBeverage, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{{beverage-id}")
	public ResponseEntity<Object> deleteBeverage(@Valid @NotNull @PathVariable ("user-id") Long beverageId, BindingResult errors){
		
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		if (!_beverageService.existBeverage(beverageId))
			return ResponseHandler.getResponse("there is no beverage id: " + beverageId, HttpStatus.BAD_REQUEST);
		
		_beverageService.deleteById(beverageId);
		return ResponseHandler.getResponse( HttpStatus.OK);
	}
	
	@DeleteMapping("/{{beverage-id}")
	public ResponseEntity<Object> deleteRawMaterialInBeverage(@RequestBody String RawMaterialName ,@Valid @NotNull @PathVariable ("user-id") Long beverageId, BindingResult errors){
		
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		if (!_beverageService.existBeverage(beverageId))
			return ResponseHandler.getResponse("there is no beverage id: " + beverageId, HttpStatus.BAD_REQUEST);

		return ResponseHandler.getResponse( HttpStatus.OK);
	}
	
	
	

}
