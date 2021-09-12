package cybersoft.java11.group8.pizza_store.fb_category.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.Beverage;
import cybersoft.java11.group8.pizza_store.fb_category.repository.BeverageRepository;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniqueBeverageName;

public class UniqueBeverageNameValidator implements ConstraintValidator<UniqueBeverageName, String> {

	private BeverageRepository _BeverageRepository;
	
	private String message;
	
	@Autowired
	public UniqueBeverageNameValidator(BeverageRepository _BeverageRepository) {
		super();
		this._BeverageRepository = _BeverageRepository;
	}

	@Override
	public void initialize(UniqueBeverageName constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		
		Optional<Beverage> beverage = _BeverageRepository.findByName(name);
		if (beverage.isEmpty())
			return true;
		
		context.buildConstraintViolationWithTemplate(message).
		addConstraintViolation().
		disableDefaultConstraintViolation();
		
		return false;
	}
}
