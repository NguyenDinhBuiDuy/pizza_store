package cybersoft.java11.group8.pizza_store.order.service;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java11.group8.pizza_store.common_data.GenericServiceImpl;
import cybersoft.java11.group8.pizza_store.fb_category.model.FBCategory;
import cybersoft.java11.group8.pizza_store.fb_category.model.FB_Type;
import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.Beverage;
import cybersoft.java11.group8.pizza_store.fb_category.model.pizza.Pizza;
import cybersoft.java11.group8.pizza_store.fb_category.repository.BeverageRepository;
import cybersoft.java11.group8.pizza_store.fb_category.repository.PizzaRepository;
import cybersoft.java11.group8.pizza_store.order.dto.CreateOrderDTO;
import cybersoft.java11.group8.pizza_store.order.dto.CreateOrderDetailDTO;
import cybersoft.java11.group8.pizza_store.order.model.Order;
import cybersoft.java11.group8.pizza_store.order.model.OrderDetail;
import cybersoft.java11.group8.pizza_store.order.model.TableNumber;
import cybersoft.java11.group8.pizza_store.order.repository.OrderDetailRepository;
import cybersoft.java11.group8.pizza_store.order.repository.TableNumberRepository;
import cybersoft.java11.group8.pizza_store.order.util.TableStatus;
import cybersoft.java11.group8.pizza_store.util.MapDTOToModel;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TableNumberServiceImpl extends GenericServiceImpl<TableNumber, Long> implements TableNumberService {
	@Autowired
	private TableNumberRepository tableNumberRepository;
	private PizzaRepository _pizzaRepository;
	private BeverageRepository _BeverageRepository;
	private MapDTOToModel mapper;
	
	@Override
	public TableStatus getTableStatus(TableStatus tableStatus) {
		return tableStatus;
	}
	
//	@Override
//	public OrderDetail save(@Valid CreateOrderDetailDTO dto) {
//		OrderDetail model = new OrderDetail();
//		model = (OrderDetail) mapper.map(dto, model);
//		FBCategory category;
//		String code = dto.getFbCategoryCode();
//		
//		switch (code.charAt(0)) {
//		case 'P':
//			category = _pizzaRepository.findByCode(code).get();
//			
//			model.addPizza((Pizza)category);
//			break;
//		case 'B':
//			category = _BeverageRepository.findByCode(code).get();
//			
//			model.addBeverage((Beverage)category);
//			break;
//
//		}
//		
//		return _orderDetailRepository.save(model);
//		
//	}
	
//	@Override
//	public boolean existTableNumber(Long tableId) {
//	
//		return tableNumberRepository.existsById(tableId);
//	}



//	@Override
//	public OrderDetail update(@Valid CreateOrderDetailDTO dto, Long orderDetailId) {
//		OrderDetail model = _orderDetailRepository.getOne(orderDetailId);
//		model = (OrderDetail) mapper.map(dto, model);
//		
//		// get FB category from database; 
//		
//		FBCategory category;
//		String code = dto.getFbCategoryCode();
//		
//		switch (code.charAt(0)) {
//		case 'P':
//			category = _pizzaRepository.findByCode(code).get();
//			
//			model.addPizza((Pizza)category);
//			break;
//		case 'B':
//			category = _BeverageRepository.findByCode(code).get();
//			
//			model.addBeverage((Beverage)category);
//			break;
//
//		}
//		
//		return _orderDetailRepository.save(model);
//	}
//
}
