package cybersoft.java11.group8.pizza_store.order.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import cybersoft.java11.group8.pizza_store.common_data.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pizza_store_order")
public class Order extends AbstractEntity {

	@Positive
	Integer billNumber;
	
	@Positive
	Long totalPayment;
	
	@OneToMany (mappedBy = "order", cascade = CascadeType.ALL)
	Set<TableNumber> tableNumbers = new HashSet<TableNumber>();
	
	@OneToMany (mappedBy = "order", cascade = CascadeType.ALL)
	Set<OrderDetail> orderDetails = new HashSet<>();

	public Order addOrderDetail(@Valid OrderDetail orderDetail) {
		this.orderDetails.add(orderDetail);
		orderDetail.setOrder(this);
		
		return this;
		
	}

	public Order addTableNumber(@Valid @NotBlank TableNumber tableNumber) {
		this.tableNumbers.add(tableNumber);
		tableNumber.setOrder(this);
		
		return this;
		
		
	}
	public Order totalPaymentCaculation() {
		for (OrderDetail orderDetail : orderDetails) {
			this.totalPayment = orderDetail.getBeverage().getPrice() * orderDetail.getQuantity();
		}
		return this;
	}
	
}
