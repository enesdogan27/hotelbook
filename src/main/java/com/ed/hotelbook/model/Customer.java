package com.ed.hotelbook.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	
	@OneToMany(mappedBy="customer")
	private List<Reservation> reservation;
}
