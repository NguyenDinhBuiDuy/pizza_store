package cybersoft.java11.group8.pizza_store.fb_category.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.Beverage;
import cybersoft.java11.group8.pizza_store.fb_category.model.pizza.PizzaTopping;
import cybersoft.java11.group8.pizza_store.fb_category.repository.BeverageRepository;
import cybersoft.java11.group8.pizza_store.fb_category.repository.ToppingRepository;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.ExistToppingName;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniqueBeverageCode;

public class ExistToppingNameValidator implements ConstraintValidator<ExistToppingName, String> {

	

	private ToppingRepository _toppingRepository;
	
	private String message;
	
	@Autowired
	public ExistToppingNameValidator(ToppingRepository _toppingRepository) {
		super();
		this._toppingRepository = _toppingRepository;
	}
	
	@Override
	public void initialize(ExistToppingName constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String toppingName, ConstraintValidatorContext context) {
		
		Optional<PizzaTopping> topping = _toppingRepository.findByName(toppingName);
		
		if (topping.isPresent())
			return true;
		
		context.buildConstraintViolationWithTemplate(message).
		addConstraintViolation().
		disableDefaultConstraintViolation();
		
		return false;
	}

}
