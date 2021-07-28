package cybersoft.java11.group8.pizza_store.role.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import cybersoft.java11.group8.pizza_store.role.validation.validator.ExistUserNameAtUsersValidator;


@Constraint(validatedBy = ExistUserNameAtUsersValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistUserNameAtUsers {
	
	public String message() default "user name is not existed at users";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};
}
