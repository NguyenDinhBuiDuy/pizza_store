package cybersoft.java11.group8.pizza_store.order.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cybersoft.java11.group8.pizza_store.common_data.model.AbstractEntity;
import cybersoft.java11.group8.pizza_store.order.dto.UpdateTableNumberDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pizza_store_order")
public class Order extends AbstractEntity {

	@Positive
	private Integer billNumber;
	
	@Positive
	private Long totalPayment;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(name = "pizza_store_table_number_order",
				joinColumns = @JoinColumn(name = "table_number"),
				inverseJoinColumns = @JoinColumn(name = "order_id"))
	@JsonIgnore
	private Set<TableNumber> tableNumber = new HashSet<>();
	
	@OneToMany (mappedBy = "order", cascade = CascadeType.ALL)
	private Set<OrderDetail> orderDetails = new HashSet<>();

	public Order addOrderDetail(@Valid OrderDetail orderDetail) {
		this.orderDetails.add(orderDetail);
		orderDetail.setOrder(this);
		return this;
	}
	
	public Order updateTableNumberToOrder(TableNumber tableNumber) {
		this.tableNumber.add(tableNumber);
		tableNumber.getTableNumber().add(this);
		return this;
	}
}