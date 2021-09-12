package cybersoft.java11.group8.pizza_store.role.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.role.service.RoleService;
import cybersoft.java11.group8.pizza_store.role.validation.annotation.UniqueRoleName;


public class UniqueRolenameValidator implements ConstraintValidator<UniqueRoleName, String> {
	
	private RoleService _service;
	private String message;
	
	@Autowired
	public UniqueRolenameValidator(RoleService _service) {
		super();
		this._service = _service;
	}

	@Override
	public void initialize (UniqueRoleName constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String rolename, ConstraintValidatorContext context) {
		boolean isTakenRolename = _service.isTakenRolename(rolename);
		if (!isTakenRolename)
			return true;
		
		context.buildConstraintViolationWithTemplate(message)
		.addConstraintViolation()
		.disableDefaultConstraintViolation();
		return false;
	}
}
