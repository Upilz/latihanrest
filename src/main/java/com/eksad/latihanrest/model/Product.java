package com.eksad.latihanrest.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name="product")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name ="type")
public class Product {

	@Id
	/*@GeneratedValue(strategy = GenerationType.SEQUENCE, //why sequence? seperti numbering counter di database
	//nah nilai yang di counter itu yang dijadiin buat sequence id
	//generate auto / entity : berpatokan pada hasil balikan dari databasenya. save database dulu baru dapet default id nya.
	//dpake karena default valuenya gak ada
		generator = "product_id" )
	@SequenceGenerator(name ="product_id", 
						sequenceName = "product_id_seq",
						allocationSize = 1	)
	private Long id;*/
	
	
	//kalo default valuenya gak ada pake identity
	//ID PRODUK
	@ApiModelProperty(value = "Product's ID (Primary Key)")
	@GeneratedValue(strategy = GenerationType.IDENTITY,
			generator = "product_id" )
	@SequenceGenerator(name ="product_id_seq", sequenceName = "product_id_seq")
	private Long id;
	
	//ID BRAND
	@ApiModelProperty(value = "Brand ID (Primary Key)")
	@ManyToOne //kasitau ke jpa kalo dibawah ini sebuah relasi
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	//tambahan pak kevin
	@ApiModelProperty(value = "Brand ID (Primary Key)")
	@Transient //variabel tidak terbaca sebagai kolom di database
	private Long brandId;
	
	@ApiModelProperty(value = "Product's Name")
	@Column(nullable = false)
	private String name;
	
	@ApiModelProperty(value = "Product's Price")
	@Column(nullable = false)
	private BigDecimal price; 
}
