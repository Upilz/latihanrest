package com.eksad.latihanrest.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@DiscriminatorValue("Cashier") //tanpa discriminator value gak ada atau gak diisi juga direct langsung panggil class
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)

public class Cashier extends Person {

	private String shift;
}
