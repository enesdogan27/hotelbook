package com.ed.hotelbook;

import static org.junit.Assert.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ed.hotelbook.model.Customer;
import com.ed.hotelbook.model.Reservation;
import com.ed.hotelbook.model.ReservationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
public class ReservationRestControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ReservationRepository reservationRepository;

	private Customer customer;

	private Reservation reservation;

	@Before
	public void setUp() throws Exception {
		customer = Customer.builder().customerName("Edo").build();
		reservation = Reservation.builder().customer(customer).build();
	}

	@Test
	public void testSaveReservation() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String reservationAsString = objectMapper
				.writeValueAsString(reservation);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/reservation")
				.contentType(APPLICATION_JSON).characterEncoding("utf-8")
				.content(reservationAsString)).andExpect(status().isOk());

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/reservation")
				.contentType(APPLICATION_JSON).characterEncoding("utf-8"))
				.andExpect(status().isOk()).andReturn();
		
		System.out.println("Content is ::: " + result.getResponse().getContentAsString());
	}

}
