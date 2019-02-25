package com.ed.hotelbook;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ed.hotelbook.model.Room;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
public class RoomRestControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	private Room room;
	
	private String roomApi = "/api/room/";

	@Before
	public void setUp() throws Exception {
		room = Room.builder().roomNumber("1").roomStatus(Room.RoomStatus.Vacant).
				roomType(Room.RoomType.Standard).build();
	}

	@Test
	public void testSaveRoom() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String roomAsString = objectMapper
				.writeValueAsString(room);

		mockMvc.perform(MockMvcRequestBuilders.post(roomApi)
				.contentType(APPLICATION_JSON).characterEncoding("utf-8")
				.content(roomAsString)).andExpect(status().isOk());

		mockMvc.perform(MockMvcRequestBuilders.get(roomApi)
				.contentType(APPLICATION_JSON).characterEncoding("utf-8"))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].roomId").value("1"))
				.andReturn();
	}
}
