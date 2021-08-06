package cybersoft.java11.group8.pizza_store.fb_category.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import cybersoft.java11.group8.pizza_store.common_data.model.AbstractEntity;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniqueBeverageCode;
import cybersoft.java11.group8.pizza_store.fb_category.validation.annotation.UniqueBeverageName;
import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class FBCategory extends AbstractEntity{
	
	@NotBlank (message = "{fbcategory.code.notblank}")
	@Column (unique = true)
	protected String code;
	
	@NotBlank (message = "{fbcategory.name.notblank}")
	@Column (unique = true)
	protected String name;
	
	@NotNull (message = "{fbcategory.price.notnull}")
	@Positive (message = "{fbcategory.price.positive}")
	protected Long price;
	
	@NotNull (message = "{fbcategory.type.notnull}")
	protected FB_Type type;
	
	@NotNull
	protected FB_Status status;
	
	protected String description;
	
}
