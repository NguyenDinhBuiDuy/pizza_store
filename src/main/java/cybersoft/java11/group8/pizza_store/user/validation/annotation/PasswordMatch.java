package cybersoft.java11.group8.pizza_store.user.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import cybersoft.java11.group8.pizza_store.user.validation.validator.PasswordMatchValidator;

@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = PasswordMatchValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatch {
	String message() default "Confirm password must match";
	
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    String first();
    String second();

}
