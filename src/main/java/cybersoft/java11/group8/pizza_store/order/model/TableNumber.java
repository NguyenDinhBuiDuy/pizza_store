package cybersoft.java11.group8.pizza_store.order.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

import cybersoft.java11.group8.pizza_store.common_data.model.AbstractEntity;
import cybersoft.java11.group8.pizza_store.order.util.TableStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pizza_store_table_number")
public class TableNumber extends AbstractEntity {
	
	@Positive
	Integer tableNumber;
	
	TableStatus tableStatus;
	
	Integer tableSize;
	
	@ManyToOne (fetch = FetchType.LAZY)
	Order order ;
	

}
