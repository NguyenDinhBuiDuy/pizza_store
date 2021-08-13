package cybersoft.java11.group8.pizza_store.order.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cybersoft.java11.group8.pizza_store.common_data.model.AbstractEntity;
import cybersoft.java11.group8.pizza_store.fb_category.model.FBCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "pizza_store_order_detail")
public class OrderDetail extends AbstractEntity{
	
	@OneToOne
	FBCategory fbCategory ;
	Integer quantity;
	Long price = fbCategory.getPrice()*quantity;
}
