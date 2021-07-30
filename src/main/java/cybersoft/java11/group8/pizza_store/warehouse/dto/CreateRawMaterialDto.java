package cybersoft.java11.group8.pizza_store.warehouse.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRawMaterialDto {
	private String name;
	private String unit;
	private Integer quantity;
	private Float price;
	private LocalDateTime importDate;
	private LocalDateTime expirationDate;
	private String supplier;
	private String status;
}
