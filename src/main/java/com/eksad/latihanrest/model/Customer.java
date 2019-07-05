package com.eksad.latihanrest.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

public class Customer extends Person
{
	@Column(name="card_num")
	private String cardNum;
}
