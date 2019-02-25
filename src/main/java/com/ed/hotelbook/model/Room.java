package com.ed.hotelbook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

	@Id
	@GeneratedValue
	long roomId;

	String roomNumber;

	RoomType roomType;

	RoomStatus roomStatus;

	public enum RoomStatus {
		Vacant, Occuppied
	}

	public enum RoomType {
		Standard, Double
	}
}
