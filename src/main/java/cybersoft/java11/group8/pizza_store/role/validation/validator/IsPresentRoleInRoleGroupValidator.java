package cybersoft.java11.group8.pizza_store.role.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.role.model.RoleGroup;
import cybersoft.java11.group8.pizza_store.role.repository.RoleGroupRepository;
import cybersoft.java11.group8.pizza_store.role.validation.annotation.isPresentRoleInRoleGroup;

public class IsPresentRoleInRoleGroupValidator implements ConstraintValidator<isPresentRoleInRoleGroup, String>{
	
	@Autowired
	RoleGroupRepository _roleGroupRepository;
	String message ;
	@Override
	public void initialize(isPresentRoleInRoleGroup constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		boolean isExistRolename = true;
		if (isExistRolename) {
			
			return true;
		}

		context.buildConstraintViolationWithTemplate(message).addConstraintViolation()
				.disableDefaultConstraintViolation();

		return false;
	}

}
