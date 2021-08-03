package cybersoft.java11.group8.pizza_store.fb_category.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.fb_category.repository.BeverageRepository;
import cybersoft.java11.group8.pizza_store.fb_category.repository.PizzaRepository;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniqueBeverageCode;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniquePizzaCode;

public class UniquePizzaCodeValidator implements ConstraintValidator<UniquePizzaCode, String> {

	@Autowired
	PizzaRepository _PizzaRepository;
	String message;
	
	@Override
	public void initialize(UniquePizzaCode constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String code, ConstraintValidatorContext context) {
		
//		boolean isExistCode = _PizzaRepository.findByCode(code);
//		if (isExistCode)
//			return true;
//		
		context.buildConstraintViolationWithTemplate(message).
		addConstraintViolation().
		disableDefaultConstraintViolation();
		
		return false;
	}

}
