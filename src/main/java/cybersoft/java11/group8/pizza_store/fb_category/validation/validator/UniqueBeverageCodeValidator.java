package cybersoft.java11.group8.pizza_store.fb_category.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.Beverage;
import cybersoft.java11.group8.pizza_store.fb_category.repository.BeverageRepository;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniqueBeverageCode;

public class UniqueBeverageCodeValidator implements ConstraintValidator<UniqueBeverageCode, String> {

	@Autowired
	BeverageRepository _BeverageRepository;
	
	String message;
	
	@Override
	public void initialize(UniqueBeverageCode constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String code, ConstraintValidatorContext context) {
		
		Optional< Beverage>  beverage = _BeverageRepository.findByCode(code);
		if (beverage.isPresent())
			return true;
		
		context.buildConstraintViolationWithTemplate(message).
		addConstraintViolation().
		disableDefaultConstraintViolation();
		
		return false;
	}

}
