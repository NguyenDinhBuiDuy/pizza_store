package cybersoft.java11.group8.pizza_store.fb_category.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniqueFBCategoryCode;

public class UniqueFBCategoryCodeValidator implements ConstraintValidator<UniqueFBCategoryCode, String> {

	
	String message;
	
	@Override
	public void initialize(UniqueFBCategoryCode constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String code, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}

}
