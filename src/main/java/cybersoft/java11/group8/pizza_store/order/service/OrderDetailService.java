package cybersoft.java11.group8.pizza_store.order.service;

import javax.validation.Valid;

import cybersoft.java11.group8.pizza_store.common_data.GenericService;
import cybersoft.java11.group8.pizza_store.order.dto.CreateOrderDTO;
import cybersoft.java11.group8.pizza_store.order.dto.CreateOrderDetailDTO;
import cybersoft.java11.group8.pizza_store.order.model.OrderDetail;

public interface OrderDetailService extends GenericService<OrderDetail, Long>{

	boolean existOrderDetail(Long orderDetailId);

	OrderDetail save(@Valid CreateOrderDetailDTO dto);


	OrderDetail update(@Valid CreateOrderDetailDTO dto, Long orderDetailId);

}
