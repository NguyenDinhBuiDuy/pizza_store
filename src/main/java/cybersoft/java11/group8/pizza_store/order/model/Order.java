package cybersoft.java11.group8.pizza_store.order.model;

import java.util.HashSet;
import java.util.Set;

import cybersoft.java11.group8.pizza_store.common_data.model.AbstractEntity;

public class Order extends AbstractEntity{
	
	Integer billNumber;
	Integer tableNumber;
	Long totalPayment;
	Set<OrderDetail> orderDetails = new HashSet<>();
	
}
