package cybersoft.java11.group8.pizza_store.fb_category.model.pizza;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import cybersoft.java11.group8.pizza_store.common_data.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pizza_store_pizza_topping")
public class PizzaTopping  extends AbstractEntity{

	String sauceName;

	@ManyToMany(mappedBy = "toppings", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	List<Pizza> pizzas = new LinkedList<Pizza>();
}
