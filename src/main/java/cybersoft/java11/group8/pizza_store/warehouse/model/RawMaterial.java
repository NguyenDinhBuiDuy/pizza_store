package cybersoft.java11.group8.pizza_store.warehouse.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cybersoft.java11.group8.pizza_store.common_data.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pizza_store_raw_material")
public class RawMaterial extends AbstractEntity {
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Supplier supplier;
	
	private String status;
}
