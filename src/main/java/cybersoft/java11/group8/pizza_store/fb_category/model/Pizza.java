package cybersoft.java11.group8.pizza_store.fb_category.model;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import cybersoft.java11.group8.pizza_store.fb_category.util.FBCategory;
import cybersoft.java11.group8.pizza_store.fb_category.util.pizza.PizzaDough;
import cybersoft.java11.group8.pizza_store.fb_category.util.pizza.PizzaSauce;
import cybersoft.java11.group8.pizza_store.fb_category.util.pizza.PizzaTopping;
import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "pizza_store_pizza")
public class Pizza extends FBCategory {
	
	@NotNull(message = "{pizza.dough.notnull}")
	private PizzaDough dough;
	
	private EnumSet<PizzaTopping> enumToppings = EnumSet.allOf(PizzaTopping.class);
	
//	@NotNull(message = "{pizza.topping.notnull}")
//	private Set<PizzaTopping> toppings = new HashSet<PizzaTopping>(enumToppings);
	
	
//	@NotNull(message = "{pizza.sauce.notnull}")
//	private EnumSet<PizzaSauce> sauce;
	
	@ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable (name = "raw_material_pizza_rescipes_links", 
	joinColumns = @JoinColumn (name = "pizza_id"),
	inverseJoinColumns = @JoinColumn(name = "recipes_id"))
	private Set<RawMaterial> recipes = new HashSet<RawMaterial>();
	
	

}
