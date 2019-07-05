package com.eksad.latihanrest.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntitiy {
	//mapped superclass jpa inheritance mapping
		@Id
		@GeneratedValue (strategy = GenerationType.IDENTITY)
		private Long id;
		
}
