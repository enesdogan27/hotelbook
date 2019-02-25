package com.ed.hotelbook.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
property = "roomId")
public class Room {

	@Id
	@GeneratedValue
	private long roomId;

	private String roomNumber;

	private RoomType roomType;

	private RoomStatus roomStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reservation_reservationId")
	private Reservation reservation;

	public enum RoomStatus {
		Vacant, Occuppied
	}

	public enum RoomType {
		Standard, Double
	}
}
