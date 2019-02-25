package com.ed.hotelbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ed.hotelbook.model.Room;
import com.ed.hotelbook.service.RoomService;

@RestController("/api/room")
public class RoomRestController {

	@Autowired
	public RoomService roomService;
	
	RoomRestController(RoomService roomService){
		this.roomService = roomService;
	}
	
	@GetMapping
	public List<Room> getAll(){
		return roomService.findAll();
	}
	
	@PostMapping
	public Room saveRoom(@RequestBody Room room) {
		return roomService.saveRoom(room);
	}
}
