package cybersoft.java11.group8.pizza_store.order.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.validation.constraints.Positive;

import cybersoft.java11.group8.pizza_store.common_data.model.AbstractEntity;
import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.Beverage;
import cybersoft.java11.group8.pizza_store.fb_category.model.pizza.Pizza;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "pizza_store_order_detail")
public class OrderDetail extends AbstractEntity{
	
	@Positive
	private Integer quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Pizza pizza ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Beverage beverage ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Order order;

	public OrderDetail addPizza(Pizza pizza) {
		this.pizza = pizza;
		pizza.getOderDetails().add(this);
		return this;
	}

	public OrderDetail addBeverage(Beverage beverage) {
		this.beverage = beverage;
		beverage.getOderDetails().add(this);
		return this;
	}
}

