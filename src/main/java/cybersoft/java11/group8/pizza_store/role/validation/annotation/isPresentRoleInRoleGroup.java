package cybersoft.java11.group8.pizza_store.role.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import cybersoft.java11.group8.pizza_store.role.validation.validator.IsPresentRoleInRoleGroupValidator;


@Constraint(validatedBy = IsPresentRoleInRoleGroupValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface isPresentRoleInRoleGroup {
	
	public String message() default "Rolename is presented in role group";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};
}
