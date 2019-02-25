package com.ed.hotelbook.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.ed.hotelbook.config.Views;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
property = "reservationId")
public class Reservation {

	@Id
	@GeneratedValue
	private long reservationId;

	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_customerId")
	private Customer customer;
	
	@Singular
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation")
	private List<Room> rooms;
	
	public void addRoom(Room room) {
		room.setReservation(this);
		rooms.add(room);
	}
	
	public void removeRoom(Room room) {
		rooms.remove(room);
		room.setReservation(null);
	}
}
