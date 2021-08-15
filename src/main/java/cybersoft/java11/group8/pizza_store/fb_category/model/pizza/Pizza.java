package cybersoft.java11.group8.pizza_store.fb_category.model.pizza;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cybersoft.java11.group8.pizza_store.fb_category.model.FBCategory;
import cybersoft.java11.group8.pizza_store.order.model.OrderDetail;
import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "pizza_store_pizza")
public class Pizza extends FBCategory {
	
	
	private static final Logger log = LoggerFactory.getLogger(Pizza.class);

	
	private PizzaDough dough;

	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable (name = "pizza_topping_links", 
	joinColumns = @JoinColumn(name = "pizza_id"),
	inverseJoinColumns = @JoinColumn(name = "topping_id"))
	@JsonIgnore
	private Set<PizzaTopping> toppings = new HashSet<PizzaTopping>();
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable (name = "pizza_sauce_links", 
	joinColumns = @JoinColumn(name = "pizza_id"),
	inverseJoinColumns = @JoinColumn(name = "sauce_id"))
	private Set<PizzaSauce> sauces = new HashSet<PizzaSauce>();
	
	@ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable (name = "raw_material_pizza_rescipes_links", 
	joinColumns = @JoinColumn (name = "pizza_id"),
	inverseJoinColumns = @JoinColumn(name = "recipes_id"))
	private Set<RawMaterial> recipes = new HashSet<RawMaterial>();
	
	@OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL)
	private Set<OrderDetail> oderDetails = new HashSet<OrderDetail>();

	public Pizza addRawMaterial(RawMaterial rawMaterial) {
		 this.recipes.add(rawMaterial);
		 rawMaterial.getPizzas().add(this);

		 return this;
	}

	public Pizza addTopping(PizzaTopping topping) {
		this.toppings.add(topping);
		topping.getPizzas().add(this);
		
		return this;
	}

	public boolean removeRawMaterial(RawMaterial rawMaterial) {
		
		boolean result = this.recipes.remove(rawMaterial);
		if (result)
			rawMaterial.getPizzas().remove(this);
		
		return result;	
	}

	public boolean removeTopping(PizzaTopping pizzaTopping) {
		
		boolean result = this.toppings.remove(pizzaTopping);
		if (result)
			pizzaTopping.getPizzas().remove(this);
		
		return result;
		
	}
	
	

}
