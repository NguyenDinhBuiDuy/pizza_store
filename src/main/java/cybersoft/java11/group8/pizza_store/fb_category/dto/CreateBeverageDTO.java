package cybersoft.java11.group8.pizza_store.fb_category.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import cybersoft.java11.group8.pizza_store.fb_category.model.FB_Status;
import cybersoft.java11.group8.pizza_store.fb_category.model.FB_Type;
import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.DrinkType;
import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.SugarPercent;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniqueFBCategoryCode;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniqueFBCategoryName;

public class CreateBeverageDTO {
	
	@UniqueFBCategoryCode
	@NotBlank (message = "{fbcategory.code.notblank}")
	String code;
	
	@UniqueFBCategoryName
	@NotBlank (message = "{fbcategory.name.notblank}")
	String name;
	
	@NotNull (message = "{fbcategory.price.notnull}")
	@Positive (message = "{fbcategory.price.positive}")
	Long price;
	
	String description;

}
