package cybersoft.java11.group8.pizza_store.warehouse.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import cybersoft.java11.group8.pizza_store.util.DateUtils;
import cybersoft.java11.group8.pizza_store.warehouse.util.RawMaterialStatus;
import cybersoft.java11.group8.pizza_store.warehouse.validation.annotation.UniqueRawMaterialName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRawMaterialDto {

	@UniqueRawMaterialName 
	private String name;
	
	@NotBlank
	private String unit;
	
	@NotNull
	private Integer quantity;
	
	@NotNull
	private Float price;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
	@DateTimeFormat (pattern = DateUtils.DATE_FORMAT)
	private LocalDateTime importDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
	@DateTimeFormat (pattern = DateUtils.DATE_FORMAT)
	private LocalDateTime expirationDate;
	
	private RawMaterialStatus status;
	
	String Supplier;
}
