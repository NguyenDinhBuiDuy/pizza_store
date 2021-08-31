package cybersoft.java11.group8.pizza_store.order.service;

import cybersoft.java11.group8.pizza_store.common_data.GenericService;
import cybersoft.java11.group8.pizza_store.order.model.TableNumber;
import cybersoft.java11.group8.pizza_store.order.util.TableStatus;

public interface TableNumberService extends GenericService<TableNumber, Long>{

//	boolean existTableNumber(Long tableId);

//	OrderDetail save(@Valid CreateOrderDetailDTO dto);
//
//
//	OrderDetail update(@Valid CreateOrderDetailDTO dto, Long orderDetailId);

	TableStatus getTableStatus(TableStatus tableStatus);
}
