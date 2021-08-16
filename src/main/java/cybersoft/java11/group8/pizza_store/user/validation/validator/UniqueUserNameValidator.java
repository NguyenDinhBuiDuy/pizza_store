package cybersoft.java11.group8.pizza_store.user.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.user.service.UserService;
import cybersoft.java11.group8.pizza_store.user.validation.annotation.UniqueUsername;


public class UniqueUserNameValidator implements ConstraintValidator<UniqueUsername, String> {
	@Autowired
	private UserService _service;
	private String message;

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		boolean isTakenUserName = _service.isTakenUsername(username);
		if (!isTakenUserName)
			return true;

		context.buildConstraintViolationWithTemplate(message)
				.addConstraintViolation()
				.disableDefaultConstraintViolation();
		return false;
	}

	@Override
	public void initialize(UniqueUsername constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}

}
