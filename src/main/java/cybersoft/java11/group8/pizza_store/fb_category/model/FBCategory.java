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
	
	@Column (unique = true)
	protected String code;
	
	@Column (unique = true)
	protected String name;
	
	protected Long price;
	
	protected FB_Type type;
	
	protected FB_Status status;
	
	protected String description;
	
}
