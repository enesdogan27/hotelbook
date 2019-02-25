package com.ed.hotelbook.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
property = "customerId")
public class Customer {

	public Customer() {
	}

	@Id
	@GeneratedValue
	private long customerId;

	private String customerName;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reservation> reservations;

	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
		reservation.setCustomer(this);
	}
	
	public void removeReservation(Reservation reservation) {
		reservations.remove(reservation);
		reservation.setCustomer(null);
	}
}
