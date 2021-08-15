package cybersoft.java11.group8.pizza_store.order.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.java11.group8.pizza_store.common_data.model.ResponseHandler;
import cybersoft.java11.group8.pizza_store.order.dto.CreateOrderDTO;
import cybersoft.java11.group8.pizza_store.order.model.Order;
import cybersoft.java11.group8.pizza_store.order.model.OrderDetail;
import cybersoft.java11.group8.pizza_store.order.model.TableNumber;
import cybersoft.java11.group8.pizza_store.order.service.OrderDetailService;
import cybersoft.java11.group8.pizza_store.order.service.OrderService;
import cybersoft.java11.group8.pizza_store.order.service.TableNumberService;
import cybersoft.java11.group8.pizza_store.order.util.TableStatus;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping ("/api/odrer")
public class OrderController {
	
	OrderService _orderService;
	OrderDetailService _orderDetailService;
	TableNumberService _tableNumberService;
	
	@GetMapping("")
	public ResponseEntity<Object> findAllOrders(){
		List<Order> oders = _orderService.findAll();
		if (oders.isEmpty()) {
			return ResponseHandler.getResponse("there is no data", HttpStatus.BAD_REQUEST);
		}
		return ResponseHandler.getResponse(oders, HttpStatus.OK);
	}
	@GetMapping("/{order-id}")
	public ResponseEntity<Object> findOrderById(@Valid @PathVariable("order-id") Long orderId){
		
		Optional<Order> order = _orderService.findById(orderId);
		if (order.isEmpty()) {
			return ResponseHandler.getResponse( "there is no data", HttpStatus.BAD_REQUEST);
		}
		
		return ResponseHandler.getResponse(order, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> saveOrder(@Valid @RequestBody CreateOrderDTO dto, BindingResult errors){
		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}
		Order order  = new Order();
		order = _orderService.save(dto);
		return ResponseHandler.getResponse(order, HttpStatus.CREATED);
	}
	
	@PutMapping("/{order-id}")
	public ResponseEntity<Object> updateOrder(@Valid CreateOrderDTO dto, @PathVariable ("order-id") Long orderId, BindingResult errors){
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		if (!_orderService.existOrder(orderId))
			return ResponseHandler.getResponse("there is no order id: " + orderId, HttpStatus.BAD_REQUEST);
		
		Order updateOrder  = new Order();
		updateOrder = _orderService.update(dto, orderId);
		return ResponseHandler.getResponse(updateOrder, HttpStatus.CREATED);
	}
	
	@PutMapping("/{order-id}/order_detail")
	public ResponseEntity<Object> addOderDetail(@Valid @RequestBody OrderDetail orderDetail , @PathVariable ("order-id") Long orderId, BindingResult errors){
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
			
		if (!_orderService.existOrder(orderId))
			return ResponseHandler.getResponse("there is no order id: " + orderId, HttpStatus.BAD_REQUEST);
		
		if (!_orderDetailService.existOrderDetail(orderDetail.getId()))
			return ResponseHandler.getResponse("there is no order detail id: " + orderDetail.getId(), HttpStatus.BAD_REQUEST);
		
		Order updateOrder  = new Order();
		updateOrder = _orderService.addOrderDetail(orderDetail,orderId);
		return ResponseHandler.getResponse(updateOrder, HttpStatus.CREATED);
	}
	
	@PutMapping("/{order-id}/table_number")
	public ResponseEntity<Object> addTableNumber(@Valid @NotBlank @RequestBody TableNumber tableNumber , @PathVariable ("order-id") Long orderId, BindingResult errors){
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		if (!_orderService.existOrder(orderId))
			return ResponseHandler.getResponse("there is no order id: " + orderId, HttpStatus.BAD_REQUEST);
		
		if (!_tableNumberService.existTableNumber(tableNumber.getId()))
			return ResponseHandler.getResponse("there is no order id: " + tableNumber.getId(), HttpStatus.BAD_REQUEST);
		
		if (tableNumber.getTableStatus().equals(TableStatus.ORDERED))
			return ResponseHandler.getResponse("table is ordered", HttpStatus.BAD_REQUEST);
		
		Order updateOrder  = new Order();
		
		updateOrder = _orderService.addTableNumber(tableNumber,orderId);
		return ResponseHandler.getResponse(updateOrder, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{order-id}")
	public ResponseEntity<Object> deleteOrder( @PathVariable ("order-id") Long beverageId){
		
		if (!_orderService.existOrder(beverageId))
			return ResponseHandler.getResponse("there is no order id: " + beverageId, HttpStatus.BAD_REQUEST);
		
		_orderService.deleteById(beverageId);
		return ResponseHandler.getResponse("delete successfull", HttpStatus.OK);
	}
	
//	@DeleteMapping("/{beverage-id}/raw_material")
//	public ResponseEntity<Object> deleteOrderDetail(@RequestBody S , @PathVariable ("beverage-id") Long beverageId, BindingResult errors){
//		
//		if (errors.hasErrors())
//			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
//		
//		if (!_orderService.existBeverage(beverageId))
//			return ResponseHandler.getResponse("there is no beverage id: " + beverageId, HttpStatus.BAD_REQUEST);
//		
//		boolean result = _orderService.removeRawMeterialInBeverage(RawMaterialName, beverageId);
//		
//		if (!result) 
//			return ResponseHandler.getResponse("there is no RawMaterial: " + RawMaterialName, HttpStatus.BAD_REQUEST);
//	
//		return ResponseHandler.getResponse("remove raw material: " + RawMaterialName + "successfull", HttpStatus.OK);
//	}

}
