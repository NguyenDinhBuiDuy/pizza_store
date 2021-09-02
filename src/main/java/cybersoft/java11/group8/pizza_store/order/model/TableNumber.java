package cybersoft.java11.group8.pizza_store.order.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private Integer tableNumber;
	
	private TableStatus tableStatus;
	
	private Integer tableSize;
	
	@ManyToMany(mappedBy = "tableNumber")
	@JsonIgnore
	private Set<Order> order = new HashSet<>();
	
	public Set<Order> getTableNumber() {
		return order;
	}
}
