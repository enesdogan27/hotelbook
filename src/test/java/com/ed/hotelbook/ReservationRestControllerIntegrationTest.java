package com.ed.hotelbook;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ed.hotelbook.model.Customer;
import com.ed.hotelbook.model.Reservation;
import com.ed.hotelbook.model.Room;
import com.ed.hotelbook.model.Room.RoomStatus;
import com.ed.hotelbook.model.Room.RoomType;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
public class ReservationRestControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	private Customer customer;
	private Room room;
	private Reservation reservation;

	ObjectMapper objectMapper = new ObjectMapper();

	@Before
	public void setUp() throws Exception {
		customer = Customer.builder().customerName("Edo").build();
		room = Room.builder().roomNumber("1").roomStatus(RoomStatus.Vacant).roomType(RoomType.Standard).build();
		reservation = Reservation.builder().description("reservation test").room(room).build();
	}

	@Ignore
	@Test
	public void saveCustomer() throws Exception {
		String customerAsString = objectMapper.writeValueAsString(customer);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/customer").contentType(APPLICATION_JSON)
				.characterEncoding("utf-8").content(customerAsString)).andExpect(status().isOk())
				.andExpect(jsonPath("$.customerName", is("Edo")));
	}

	@Test
	public void saveReservation() throws Exception {
		String reservationAsString = objectMapper.writeValueAsString(reservation);
		String customerAsString = objectMapper.writeValueAsString(customer);

		 MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/customer").contentType(APPLICATION_JSON)
				.characterEncoding("utf-8").content(customerAsString)).andExpect(status().isOk())
				.andExpect(jsonPath("$.customerName", is("Edo"))).andDo(customerPostResult -> {

					Customer customerResponse = objectMapper
							.readValue(customerPostResult.getResponse().getContentAsString(), Customer.class);

					String str = mockMvc.perform(MockMvcRequestBuilders.post("/api/reservation").contentType(APPLICATION_JSON)
							.characterEncoding("utf-8").content(reservationAsString)
							.param("customerId", "1"))
							.andExpect(status().isOk()).andExpect(jsonPath("$.description", is("reservation test"))).andReturn().getResponse().getContentAsString();
							
					System.out.println(
							"*****************************" +
					str
							+"*****************************");
					
					
				
				}).andReturn();
		 
		 System.out.println();
	}

	@Ignore
	@Test
	public void testSaveReservation() throws Exception {
		String reservationAsString = objectMapper.writeValueAsString(reservation);
		String customerAsString = objectMapper.writeValueAsString(customer);

		// Persist customer
		mockMvc.perform(MockMvcRequestBuilders.post("/api/customer").contentType(APPLICATION_JSON)
				.characterEncoding("utf-8").content(customerAsString)).andExpect(status().isOk())
				.andDo(customerPersistResult ->

				// Persist reservation
				mockMvc.perform(MockMvcRequestBuilders.post("/api/reservation").contentType(APPLICATION_JSON)
						.characterEncoding("utf-8").param("customerId", String.valueOf(customer.getCustomerId()))
						.content(reservationAsString)).andExpect(status().isOk()).andDo(reservationPersistResult ->

				// Check reservation
				mockMvc.perform(MockMvcRequestBuilders.get("/api/reservation").contentType(APPLICATION_JSON)
						.characterEncoding("utf-8")).andExpect(status().isOk()).andReturn())
						.andDo(reservationGetResult ->

						// Check room
						mockMvc.perform(MockMvcRequestBuilders.get("/api/room").contentType(APPLICATION_JSON)
								.characterEncoding("utf-8")).andExpect(status().isOk()).andReturn()));
	}

}
