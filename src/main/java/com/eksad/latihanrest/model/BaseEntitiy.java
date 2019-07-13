package com.eksad.latihanrest.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntitiy {
	//mapped superclass jpa inheritance mapping
		@ApiModelProperty(value = "Brand ID (Primary Key)")
		@Id
		@GeneratedValue (strategy = GenerationType.IDENTITY)
		private Long id;
		
}
