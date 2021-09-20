package cybersoft.java11.group8.pizza_store.user.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import cybersoft.java11.group8.pizza_store.user.validation.validator.PasswordConstrainValidator;

@Constraint(validatedBy = PasswordConstrainValidator.class)
@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
	public String message() default "Password is not valid";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};

}
