package cybersoft.java11.group8.pizza_store.order.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import cybersoft.java11.group8.pizza_store.fb_category.service.BeverageService;
import cybersoft.java11.group8.pizza_store.fb_category.service.PizzaService;
import cybersoft.java11.group8.pizza_store.order.dto.CreateOrderDetailDTO;
import cybersoft.java11.group8.pizza_store.order.model.OrderDetail;
import cybersoft.java11.group8.pizza_store.order.service.OrderDetailService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/oder_detail")
public class OrderDetailController {

	OrderDetailService _orderDetailService;
	PizzaService _pizzaService;
	BeverageService _beveragService;

	@GetMapping("")
	public ResponseEntity<Object> findAllOrderDetails() {
		List<OrderDetail> oderDetails = _orderDetailService.findAll();
		if (oderDetails.isEmpty()) {
			return ResponseHandler.getResponse("there is no data", HttpStatus.BAD_REQUEST);
		}
		return ResponseHandler.getResponse(oderDetails, HttpStatus.OK);
	}

	@GetMapping("/{order-detail-id}")
	public ResponseEntity<Object> findOrderDetailById(@Valid @PathVariable("order-detail-id") Long orderDetailId) {

		Optional<OrderDetail> oderDetail = _orderDetailService.findById(orderDetailId);
		if (oderDetail.isEmpty()) {
			return ResponseHandler.getResponse("there is no data", HttpStatus.BAD_REQUEST);
		}

		return ResponseHandler.getResponse(oderDetail, HttpStatus.OK);
	}

	@PostMapping("/pizza")
	public ResponseEntity<Object> savePizzaOrderDetail(@Valid @RequestBody CreateOrderDetailDTO dto,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}
		String code = dto.getFbCategoryCode();
		if (_pizzaService.findByCode(code).isPresent())
			return ResponseHandler.getResponse("there is no pizza code " + code, HttpStatus.BAD_REQUEST);
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail = _orderDetailService.save(dto);
		return ResponseHandler.getResponse(orderDetail, HttpStatus.CREATED);
	}
	
	@PostMapping("/beverage")
	public ResponseEntity<Object> saveBeverageOrderDetail(@Valid @RequestBody CreateOrderDetailDTO dto,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		}
		String code = dto.getFbCategoryCode();
		if (_beveragService.findByCode(code).isPresent())
			return ResponseHandler.getResponse("there is no beverage code " + code, HttpStatus.BAD_REQUEST);
		OrderDetail orderDetail = new OrderDetail();
		orderDetail = _orderDetailService.save(dto);
		return ResponseHandler.getResponse(orderDetail, HttpStatus.CREATED);
	}

	@PutMapping("/{order-detail-id}/pizza")
	public ResponseEntity<Object> updatePizzaOrderDetail(@Valid CreateOrderDetailDTO dto,
			@PathVariable("order-detail-id") Long orderDetailId, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
		
		if (!_orderDetailService.existOrderDetail(orderDetailId))
			return ResponseHandler.getResponse("there is no order detail id: " + orderDetailId, HttpStatus.BAD_REQUEST);
		
		String code = dto.getFbCategoryCode();
		if (_pizzaService.findByCode(code).isPresent())
			return ResponseHandler.getResponse("there is no pizza code " + code, HttpStatus.BAD_REQUEST);
		
		
		OrderDetail updateOrderDetail = new OrderDetail();
		updateOrderDetail = _orderDetailService.update(dto, orderDetailId);
		return ResponseHandler.getResponse(updateOrderDetail, HttpStatus.CREATED);
	}
	
	@PutMapping("/{order-detail-id}/beverage")
	public ResponseEntity<Object> updateBeverageOrderDetail(@Valid CreateOrderDetailDTO dto,
			@PathVariable("order-detail-id") Long orderDetailId, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		if (!_orderDetailService.existOrderDetail(orderDetailId))
			return ResponseHandler.getResponse("there is no order detail id: " + orderDetailId, HttpStatus.BAD_REQUEST);
		
		String code = dto.getFbCategoryCode();
		if (_beveragService.findByCode(code).isPresent())
			return ResponseHandler.getResponse("there is no beverage code " + code, HttpStatus.BAD_REQUEST);
		
		OrderDetail updateOrderDetail = new OrderDetail();
		updateOrderDetail = _orderDetailService.update(dto, orderDetailId);
		return ResponseHandler.getResponse(updateOrderDetail, HttpStatus.CREATED);
	}

}
