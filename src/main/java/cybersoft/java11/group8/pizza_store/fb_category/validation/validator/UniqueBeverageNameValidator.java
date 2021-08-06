package cybersoft.java11.group8.pizza_store.fb_category.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.Beverage;
import cybersoft.java11.group8.pizza_store.fb_category.repository.BeverageRepository;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniqueBeverageName;

public class UniqueBeverageNameValidator implements ConstraintValidator<UniqueBeverageName, String> {

	@Autowired
	BeverageRepository _BeverageRepository;
	
	String message;
	
	@Override
	public void initialize(UniqueBeverageName constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		
		Optional<Beverage> beverage = _BeverageRepository.findByName(name);
		if (beverage.isPresent())
			return true;
		
		context.buildConstraintViolationWithTemplate(message).
		addConstraintViolation().
		disableDefaultConstraintViolation();
		
		return false;
	}
}
