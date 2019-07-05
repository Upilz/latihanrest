package com.eksad.latihanrest.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.criteria.Fetch;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="transaction")
@Data

public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP) //ngambil hari dan waktu,temporary jpa ngasi tau ke db apa yang mau disimpan gimana sih 
	private Date date;
	 
	private String remark;
	
	@EqualsAndHashCode.Exclude  //sama kayak to string buat data yg bolak balik
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction") //even fetch type gak di set kayak eager autpmate emang udah lazy 
	/*(cascade = CascadeType.ALL, mappedBy = "transaction",
						fetch =FetchType.EAGER ) //bikin fetch karena dia lazy tipe, change into eager
		*/ //bikin fetch karena dia lazy tipe, change into eager
		
	private Set<TransasctionDetail> details; //simpan di suatu collection krna banyak datanya
	
}
