package com.ed.hotelbook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@Builder
@AllArgsConstructor
public class Customer {
	
	public Customer() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Builder.Default
	private String customerID;
	
	@Builder.Default
	private String customerName;
}
