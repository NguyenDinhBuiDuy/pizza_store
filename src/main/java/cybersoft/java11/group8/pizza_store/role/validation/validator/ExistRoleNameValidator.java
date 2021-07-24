package cybersoft.java11.group8.pizza_store.role.validation.validator;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.role.repository.RoleRepository;
import cybersoft.java11.group8.pizza_store.role.validation.annotation.ExistRoleName;

public class ExistRoleNameValidator implements ConstraintValidator<ExistRoleName, Set<String>> {

	@Autowired
	private RoleRepository _roleRepository;

	private String message;

	@Override
	public boolean isValid(Set<String> rolename, ConstraintValidatorContext context) {

		Iterator<String> iterator = rolename.iterator();
		int i = 0;

		while (iterator.hasNext()) {
			if ((_roleRepository.findByRolename(iterator.next())) == null) {
				message = message + ("/n +" + iterator.next() + "is not exist");
				i++;
			}
		}

		if (i > 0) {
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation()
					.disableDefaultConstraintViolation();

			return false;
		}
		return true;

	}
}
