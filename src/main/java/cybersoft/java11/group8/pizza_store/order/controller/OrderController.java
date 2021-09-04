package cybersoft.java11.group8.pizza_store.order.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.java11.group8.pizza_store.common_data.model.ResponseHandler;
import cybersoft.java11.group8.pizza_store.order.dto.CreateOrderDTO;
import cybersoft.java11.group8.pizza_store.order.dto.UpdateOrderDto;
import cybersoft.java11.group8.pizza_store.order.model.Order;
import cybersoft.java11.group8.pizza_store.order.model.OrderDetail;
import cybersoft.java11.group8.pizza_store.order.service.OrderService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping ("/api/order")
public class OrderController {
	@Autowired
	private OrderService _orderService;
	
	@GetMapping("")
	public ResponseEntity<Object> findAllOrders(){
		List<Order> orders = _orderService.findAll();
		if(orders.isEmpty())
			return ResponseHandler.getResponse("no order data", HttpStatus.BAD_REQUEST);
		return ResponseHandler.getResponse(orders, HttpStatus.OK);
	}
	
	@GetMapping("/{order-id}")
	public ResponseEntity<Object> findOrderById(@PathVariable("order-id") Long orderId){
		Optional<Order> order = _orderService.findById(orderId);
		if (order.isEmpty())
			return ResponseHandler.getResponse("order not found", HttpStatus.BAD_REQUEST);
		return ResponseHandler.getResponse(order, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> saveOrder(@Valid @Positive @RequestBody CreateOrderDTO dto, BindingResult errors){
		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}
		Order order = _orderService.save(dto);
		return ResponseHandler.getResponse(order, HttpStatus.CREATED);
	}
	
	@PutMapping("/{order-id}")
	public ResponseEntity<Object> updateOrder(@Valid @Positive @RequestBody UpdateOrderDto dto, @PathVariable("order-id") Long orderId, BindingResult errors){
		if(orderId == null)
			return ResponseHandler.getResponse("Please enter order id.", HttpStatus.BAD_REQUEST);
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		if (!_orderService.existOrder(orderId))
			return ResponseHandler.getResponse("order id does not exist: " + orderId, HttpStatus.BAD_REQUEST);
		Order updateOrder = _orderService.update(dto, orderId);
		return ResponseHandler.getResponse(updateOrder, HttpStatus.OK);
	}
	
	@PostMapping("/{order-id}/order_detail")
	public ResponseEntity<Object> addOrderDetail(@Valid @RequestBody OrderDetail orderDetail , @PathVariable("order-id") Long orderId, BindingResult errors){
		if(orderId == null)
			return ResponseHandler.getResponse("Please enter order id.", HttpStatus.BAD_REQUEST);
		if(errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		if(!_orderService.existOrder(orderId))
			return ResponseHandler.getResponse("order id does not exist: " + orderId, HttpStatus.BAD_REQUEST);
		Order addOrderDetail = _orderService.addOrderDetail(orderDetail, orderId);
		return ResponseHandler.getResponse(addOrderDetail, HttpStatus.CREATED);
	}
}
