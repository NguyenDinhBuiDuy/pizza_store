package cybersoft.java11.group8.pizza_store.common_data.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import cybersoft.java11.group8.pizza_store.util.DateUtils;
import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // dung khi data base co chuc nang auto increasement
	@Column(updatable = false)
	protected long id ;
	
	@Version
	protected int version;
	
	@CreatedBy
	protected String createdBy;
	
	@LastModifiedDate
	protected String updatedBy;
		
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)// giup json hieu nen in format date nhu da quy dinh truoc
	@CreatedDate
	@DateTimeFormat (pattern = DateUtils.DATE_FORMAT)
	@Column(name = "create_at", updatable = false, nullable = false)
	protected LocalDateTime createdAt;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.DATE_FORMAT)
	@LastModifiedDate
	@DateTimeFormat (pattern = DateUtils.DATE_FORMAT)
	@Column(name = "update_at", updatable = false, nullable = false)
	protected LocalDateTime updateAt;
	
//	public AbstractEntity() {
//		createdAt = LocalDateTime.now();
//		updateAt = LocalDateTime.now();
//	}

	
	
	

}
