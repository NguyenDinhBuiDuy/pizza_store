package cybersoft.java11.group8.pizza_store.order.service;

import javax.validation.Valid;

import cybersoft.java11.group8.pizza_store.common_data.GenericService;
import cybersoft.java11.group8.pizza_store.order.dto.CreateOrderDTO;
import cybersoft.java11.group8.pizza_store.order.dto.UpdateTableNumberDto;
import cybersoft.java11.group8.pizza_store.order.model.Order;
import cybersoft.java11.group8.pizza_store.order.model.OrderDetail;

public interface OrderService extends GenericService<Order, Long>{

	Order save(@Valid CreateOrderDTO dto);

	boolean existOrder(Long orderId);

	Order update(@Valid CreateOrderDTO dto, Long orderId);

	Order addOrderDetail(@Valid OrderDetail orderDetail, Long orderId);
	
	Order updateTableNumberToOrder(@Valid UpdateTableNumberDto tableNumber, Long orderId);
}
