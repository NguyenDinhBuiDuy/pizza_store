package cybersoft.java11.group8.pizza_store.fb_category.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniqueBeverageCode;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniqueBeverageName;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniquePizzaCode;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniquePizzaName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePizzaToppingDTO {
	
	@NotBlank (message = "{fbcategory.name.notblank}")
	@Column (unique = true)
	String name;
	
}
