package cybersoft.java11.group8.pizza_store.role.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.java11.group8.pizza_store.role.validation.annotation.ExistUserNameAtUsers;
import cybersoft.java11.group8.pizza_store.user.repository.UserRepository;

public class ExistUserNameAtUsersValidator implements ConstraintValidator<ExistUserNameAtUsers, String>{
	
	@Autowired
	UserRepository _userRepository;
	String message ;
	
	@Override
	public void initialize(ExistUserNameAtUsers constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {

		boolean isExistUserName = _userRepository.findByUsername(username).isPresent();
		if (isExistUserName) {
			
			return true;
		}

		context.buildConstraintViolationWithTemplate(message).addConstraintViolation()
				.disableDefaultConstraintViolation();

		return false;
	}

}
