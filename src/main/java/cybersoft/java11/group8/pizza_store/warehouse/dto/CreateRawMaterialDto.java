package cybersoft.java11.group8.pizza_store.warehouse.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRawMaterialDto {
	@NotBlank(message = "{raw-material.name.not-blank}")
	@Size(min = 2, message = "{raw-material.name.size}")
	@Column(unique = true)
	private String name;
	
	@NotBlank(message = "{raw-material.unit.not-blank}")
	private String unit;
	
	@DecimalMin(value = "1", message = "{raw-material.quantity.min}")
	private Integer quantity;
	
	@DecimalMin(value = "1000", message = "{raw-material.price.min}")
	private Float price;
	
	private LocalDateTime importDate;
	private LocalDateTime expirationDate;
	
	private String supplier;
	
	private String status;
}
