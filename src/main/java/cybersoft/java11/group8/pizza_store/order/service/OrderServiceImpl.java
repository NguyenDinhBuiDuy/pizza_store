package cybersoft.java11.group8.pizza_store.order.service;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import cybersoft.java11.group8.pizza_store.common_data.GenericServiceImpl;
import cybersoft.java11.group8.pizza_store.order.dto.CreateOrderDTO;
import cybersoft.java11.group8.pizza_store.order.model.Order;
import cybersoft.java11.group8.pizza_store.order.model.OrderDetail;
import cybersoft.java11.group8.pizza_store.order.model.TableNumber;
import cybersoft.java11.group8.pizza_store.order.repository.OrderRepository;
import cybersoft.java11.group8.pizza_store.util.MapDTOToModel;
import lombok.AllArgsConstructor;

@Service
public class OrderServiceImpl extends GenericServiceImpl<Order, Long> implements OrderService {

	OrderRepository _orderRepository;
	MapDTOToModel mapper;
	
	@Autowired
	public OrderServiceImpl(JpaRepository<Order, Long> repository, OrderRepository _orderRepository,
			MapDTOToModel mapper) {
		super(repository);
		this._orderRepository = _orderRepository;
		this.mapper = mapper;
	}
	

	@Override
	public Order save(@Valid CreateOrderDTO dto) {
		Order model = new Order();
		model = (Order) mapper.map(dto, model);

		return _orderRepository.save(model);
	}

	@Override
	public boolean existOrder(Long orderId) {
		
		return _orderRepository.existsById(orderId);
	}

	@Override
	public Order update(@Valid CreateOrderDTO dto, Long orderId) {
		Order model = _orderRepository.getOne(orderId);
		
		model = (Order) mapper.map(dto, model);

		return _orderRepository.save(model);
	}

	@Override
	public Order addOrderDetail(@Valid OrderDetail orderDetail, Long orderId) {
		Order order = _orderRepository.getOne(orderId);
		
		order.addOrderDetail(orderDetail);
		
		return _orderRepository.save(order);
		
	}

	@Override
	public Order addTableNumber(@Valid @NotBlank TableNumber tableNumber, Long orderId) {
		Order order = _orderRepository.getOne(orderId);
		
		order.addTableNumber(tableNumber);
		
		return _orderRepository.save(order);
	}

}
