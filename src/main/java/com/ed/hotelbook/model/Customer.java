package com.ed.hotelbook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Customer {

	public Customer() {
	}

	@Id
	@GeneratedValue
	private long customerId;

	@Builder.Default
	private String customerName;
}
