package cybersoft.java11.group8.pizza_store.order.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import cybersoft.java11.group8.pizza_store.fb_category.model.FBCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderDetailDTO {
	
	@NotBlank
	String fbCategoryCode;

	@Positive
	Integer quantity;
}
