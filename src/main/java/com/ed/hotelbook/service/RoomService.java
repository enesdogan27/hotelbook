package com.ed.hotelbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ed.hotelbook.model.Room;
import com.ed.hotelbook.model.RoomRepository;

@Service
public class RoomService {

	@Autowired
	public RoomRepository roomRepository;
	
	public List<Room> findAll(){
		return roomRepository.findAll();
	}
	
	public Room saveRoom(Room room) {
		return roomRepository.save(room);
	}
}
