package cybersoft.java11.group8.pizza_store.role.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.role.repository.RoleRepository;
import cybersoft.java11.group8.pizza_store.role.validation.annotation.ExistRoleNameAtRoles;

public class ExistRoleNameValidator implements ConstraintValidator<ExistRoleNameAtRoles, String> {

	@Autowired
	private RoleRepository _roleRepository;
	private String message;

	@Override
	public void initialize(ExistRoleNameAtRoles constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String rolename, ConstraintValidatorContext context) {
		
		boolean isExistRolename = _roleRepository.findByRolename(rolename).isPresent();
		if (isExistRolename) {
			
			return true;
		}

		context.buildConstraintViolationWithTemplate(message).addConstraintViolation()
				.disableDefaultConstraintViolation();

		return false;
	}

}
