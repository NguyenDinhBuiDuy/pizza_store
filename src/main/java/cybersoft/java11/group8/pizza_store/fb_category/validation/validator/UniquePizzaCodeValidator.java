package cybersoft.java11.group8.pizza_store.fb_category.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.fb_category.model.pizza.Pizza;
import cybersoft.java11.group8.pizza_store.fb_category.repository.BeverageRepository;
import cybersoft.java11.group8.pizza_store.fb_category.repository.PizzaRepository;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniqueBeverageCode;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniquePizzaCode;

public class UniquePizzaCodeValidator implements ConstraintValidator<UniquePizzaCode, String> {

	private PizzaRepository _PizzaRepository;
	private String message;
	
	@Autowired
	public UniquePizzaCodeValidator(PizzaRepository _PizzaRepository) {
		super();
		this._PizzaRepository = _PizzaRepository;
	}

	@Override
	public void initialize(UniquePizzaCode constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String code, ConstraintValidatorContext context) {
		
		Optional<Pizza> isExistCode = _PizzaRepository.findByCode(code);
		if (isExistCode.isEmpty())
			return true;
		
		context.buildConstraintViolationWithTemplate(message).
		addConstraintViolation().
		disableDefaultConstraintViolation();
		
		return false;
	}

}
