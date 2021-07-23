package cybersoft.java11.group8.pizza_store.user.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import cybersoft.java11.group8.pizza_store.user.validation.validator.UniqueUserNameValidator;



@Constraint (validatedBy = UniqueUserNameValidator.class)
@Target(ElementType.FIELD)
@Retention( RetentionPolicy.RUNTIME) // quy dinh khi nao annotation co tac dung
public @interface UniqueUsername {;
	public String message() default "Username is already used";
	
	public Class <?>[] groups() default {};
	
	public Class < ? extends Payload> [] payload() default{};
}
