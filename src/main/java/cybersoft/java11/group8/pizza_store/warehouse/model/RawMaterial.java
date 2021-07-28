package cybersoft.java11.group8.pizza_store.warehouse.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pizza_store_raw_material")
public class RawMaterial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String unit;
	private Integer quantity;
	private Float price;
	private LocalDateTime importDate;
	private LocalDateTime expirationDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Supplier supplier;
	private String status;
}
