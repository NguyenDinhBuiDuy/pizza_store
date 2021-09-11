package cybersoft.java11.group8.pizza_store.order.service;

import cybersoft.java11.group8.pizza_store.common_data.GenericService;
import cybersoft.java11.group8.pizza_store.order.dto.CreateOrderDTO;
import cybersoft.java11.group8.pizza_store.order.model.Order;
import cybersoft.java11.group8.pizza_store.order.model.OrderDetail;

public interface OrderService extends GenericService<Order, Long>{

	Order save(CreateOrderDTO dto);

	boolean existOrder(Long orderId);

	Order update(CreateOrderDTO dto, Long orderId);

	Order addOrderDetail(OrderDetail orderDetail, Long orderId);
}
