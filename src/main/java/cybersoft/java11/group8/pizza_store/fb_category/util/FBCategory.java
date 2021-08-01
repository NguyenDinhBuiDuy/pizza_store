package cybersoft.java11.group8.pizza_store.fb_category.util;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import cybersoft.java11.group8.pizza_store.common_data.model.AbstractEntity;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniqueFBCategoryCode;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniqueFBCategoryName;
import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class FBCategory extends AbstractEntity{
	
	@UniqueFBCategoryCode
	@NotBlank (message = "{fbcategory.code.notblank}")
	String code;
	
	@UniqueFBCategoryName
	@NotBlank (message = "{fbcategory.name.notblank}")
	String name;
	
	@NotNull (message = "{fbcategory.price.notnull}")
	@Positive (message = "{fbcategory.price.positive}")
	Long price;
	
	@NotNull (message = "{fbcategory.type.notnull}")
	FB_Type type;
	
	@NotNull
	FB_Status status;
	
}
