package cybersoft.java11.group8.pizza_store.fb_category.model.beverage;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import cybersoft.java11.group8.pizza_store.fb_category.model.FBCategory;
import cybersoft.java11.group8.pizza_store.order.model.OrderDetail;
import cybersoft.java11.group8.pizza_store.warehouse.model.RawMaterial;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "pizza_store_Beverage")
public class Beverage extends FBCategory {
	
	@NotNull (message = "{beverage.sugar_percent.notnull}")
	private SugarPercent sugarPercent;
	
	@NotNull (message = "{beverage.drink_type.notnull}")
	private DrinkType drinkType;
	
	@ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable (name = "raw_material_beverage_rescipes_links", 
	joinColumns = @JoinColumn (name = "Beverage_id"),
	inverseJoinColumns = @JoinColumn(name = "recipes_id"))
	private Set<RawMaterial> recipes = new HashSet<RawMaterial>();

	@OneToMany(mappedBy = "beverage", cascade = CascadeType.ALL)
	private Set<OrderDetail> oderDetails = new HashSet<OrderDetail>();
	
	public Beverage addRawMaterial (RawMaterial rawMaterial) {
		this.recipes.add(rawMaterial);
		rawMaterial.getBeverages().add(this);
		return this;
		
	}

	public boolean removeRawMaterial(RawMaterial rawMaterial) {
		
		boolean result = this.recipes.remove(rawMaterial);
		rawMaterial.getBeverages().remove(this);
		
		return result;
	}

	

}
