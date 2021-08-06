package cybersoft.java11.group8.pizza_store.warehouse.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.fb_category.model.pizza.Pizza;
import cybersoft.java11.group8.pizza_store.fb_category.repository.BeverageRepository;
import cybersoft.java11.group8.pizza_store.fb_category.repository.PizzaRepository;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniqueBeverageName;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniquePizzaCode;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniquePizzaName;
import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;
import cybersoft.java11.group8.pizza_store.warehouse.repository.RawMaterialRepository;
import cybersoft.java11.group8.pizza_store.warehouse.validation.annotation.UniqueRawMaterialName;

public class UniqueRawmaterialValidator implements ConstraintValidator<UniqueRawMaterialName, String> {

	@Autowired
	RawMaterialRepository _rawMaterialRepository;
	
	String message;
	
	@Override
	public void initialize(UniqueRawMaterialName constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String rawMaterialName, ConstraintValidatorContext context) {
		
		Optional<RawMaterial> isExistName = _rawMaterialRepository.findByName(rawMaterialName);
		if (isExistName.isEmpty())
			return true;
		
		context.buildConstraintViolationWithTemplate(message).
		addConstraintViolation().
		disableDefaultConstraintViolation();
		
		return false;
	}
}
