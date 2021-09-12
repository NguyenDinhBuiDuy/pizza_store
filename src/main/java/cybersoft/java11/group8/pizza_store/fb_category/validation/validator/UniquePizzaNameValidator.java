package cybersoft.java11.group8.pizza_store.fb_category.validation.validator;

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

public class UniquePizzaNameValidator implements ConstraintValidator<UniquePizzaName, String> {

	PizzaRepository _pizzaRepository;
	String message;
	
	@Autowired
	public UniquePizzaNameValidator(PizzaRepository _pizzaRepository) {
		super();
		this._pizzaRepository = _pizzaRepository;
	}

	@Override
	public void initialize(UniquePizzaName constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		
		Optional<Pizza> isExistCode = _pizzaRepository.findByName(name);
		if (isExistCode.isEmpty())
			return true;
		
		context.buildConstraintViolationWithTemplate(message).
		addConstraintViolation().
		disableDefaultConstraintViolation();
		
		return false;
	}
}
