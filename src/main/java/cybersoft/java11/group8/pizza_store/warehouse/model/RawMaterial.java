package cybersoft.java11.group8.pizza_store.warehouse.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import cybersoft.java11.group8.pizza_store.common_data.model.AbstractEntity;
import cybersoft.java11.group8.pizza_store.fb_category.model.beverage.Beverage;
import cybersoft.java11.group8.pizza_store.fb_category.model.pizza.Pizza;
import cybersoft.java11.group8.pizza_store.util.DateUtils;
import cybersoft.java11.group8.pizza_store.warehouse.util.RawMaterialStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pizza_store_raw_material")
public class RawMaterial extends AbstractEntity {
	
	@Column(unique = true)
	private String name;
	
	private String unit;
	
	private Integer quantity;
	
	private Long price;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
	@DateTimeFormat (pattern = DateUtils.DATE_FORMAT)
	private LocalDateTime importDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
	@DateTimeFormat (pattern = DateUtils.DATE_FORMAT)
	private LocalDateTime expirationDate;
	
	private RawMaterialStatus status;

	@ManyToOne(fetch = FetchType.LAZY)
	private Supplier supplier;

	@ManyToMany(mappedBy = "recipes",
			cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Beverage> beverages = new HashSet<Beverage>();
	
	@ManyToMany(mappedBy = "recipes",
			cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Pizza> pizzas = new HashSet<Pizza>();
}
