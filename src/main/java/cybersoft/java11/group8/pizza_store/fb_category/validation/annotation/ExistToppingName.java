package cybersoft.java11.group8.pizza_store.fb_category.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import cybersoft.java11.group8.pizza_store.fb_category.validation.validator.ExistToppingNameValidator;
import cybersoft.java11.group8.pizza_store.fb_category.validation.validator.UniqueBeverageCodeValidator;

@Constraint(validatedBy = ExistToppingNameValidator.class)
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistToppingName {
	public String message() default "Topping name is not exist";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};

}
