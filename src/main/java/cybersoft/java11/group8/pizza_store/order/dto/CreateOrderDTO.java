package cybersoft.java11.group8.pizza_store.order.dto;

import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderDTO {
	@Positive
	private Integer billNumber;
}
