package cybersoft.java11.group8.pizza_store.order.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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
import cybersoft.java11.group8.pizza_store.order.repository.OrderDetailRepository;
import cybersoft.java11.group8.pizza_store.order.repository.TableNumberRepository;
import cybersoft.java11.group8.pizza_store.util.MapDTOToModel;
import lombok.AllArgsConstructor;

@Service
public class TableNumberServiceImpl extends GenericServiceImpl<OrderDetail, Long> implements TableNumberService {
	
	TableNumberRepository _tableNumberlRepository;
	PizzaRepository _pizzaRepository;
	BeverageRepository _BeverageRepository;
	MapDTOToModel mapper;
	
	@Autowired
	public TableNumberServiceImpl(JpaRepository<OrderDetail, Long> repository,
			TableNumberRepository _tableNumberlRepository, PizzaRepository _pizzaRepository,
			BeverageRepository _BeverageRepository, MapDTOToModel mapper) {
		super(repository);
		this._tableNumberlRepository = _tableNumberlRepository;
		this._pizzaRepository = _pizzaRepository;
		this._BeverageRepository = _BeverageRepository;
		this.mapper = mapper;
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
//	
	@Override
	public boolean existTableNumber(Long tableId) {
	
		return _tableNumberlRepository.existsById(tableId);
	}

//
//
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


}
