package com.ed.hotelbook.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ed.hotelbook.model.Room.RoomStatus;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

	public List<Room> findAll();
	
	public List<Room> findRoomsByRoomStatus(RoomStatus roomstatus);
	
}
