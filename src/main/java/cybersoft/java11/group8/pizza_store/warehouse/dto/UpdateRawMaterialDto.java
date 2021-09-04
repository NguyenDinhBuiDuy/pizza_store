package cybersoft.java11.group8.pizza_store.warehouse.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import cybersoft.java11.group8.pizza_store.util.DateUtils;
import cybersoft.java11.group8.pizza_store.warehouse.util.RawMaterialStatus;
import cybersoft.java11.group8.pizza_store.warehouse.validation.annotation.UniqueRawMaterialName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRawMaterialDto {
	@Size(min = 2, message = "{raw-material.name.size}")
	@UniqueRawMaterialName
	private String name;
	
	private String unit;
	
	@DecimalMin(value = "1", message = "{raw-material.quantity.min}")
	private Integer quantity;
	
	@DecimalMin(value = "1000", message = "{raw-material.price.min}")
	private Float price;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
	@DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
	private LocalDateTime importDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
	@DateTimeFormat(pattern = DateUtils.DATE_FORMAT)
	private LocalDateTime expirationDate;
	
	private String supplier;
	
	private RawMaterialStatus status;
}
