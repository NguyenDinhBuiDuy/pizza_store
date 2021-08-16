package cybersoft.java11.group8.pizza_store.role.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import cybersoft.java11.group8.pizza_store.role.validation.validator.ExistRoleGroupIdValidator;



@Constraint(validatedBy = ExistRoleGroupIdValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CExistRoleGroupId {
	public String message() default "role group name does not exist";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};
}

