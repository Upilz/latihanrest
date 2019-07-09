package com.eksad.latihanrest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name="product_grocery")
@PrimaryKeyJoinColumn(name="product_id")
@DiscriminatorValue("Grocery")

@RepositoryRestResource (path = "groceries")//buat singkat url pas manggilnya jadi tinggal
//localhost/groceries aja gak/productgrocery

public class ProductGrocery extends Product
{
	@Column (name="expiry_date",nullable = false)
	@Temporal(TemporalType.DATE)
	private Date expiryDate;

}
