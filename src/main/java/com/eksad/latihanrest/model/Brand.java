package com.eksad.latihanrest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@data itu singkatan dari getter setter equals
@Data
@EqualsAndHashCode(callSuper = true)//true artinya include si parent ke dalam method ini
@ToString(callSuper = true)
@Entity
@Table(name="brand")
public class Brand extends BaseEntitiy
{
	//declare anotation id dihapus karena sudah di declare di class base entity
	
	@Column(nullable = false) // karena nama di tabel dan di klass sama, tulis aja constraint null ablenya langsung
	private String name;
	
	@Column(name = "product_type") // beda nama tabel dan di klass, makanya harus di deklarkan lagi 
	private String productType;
	
}
