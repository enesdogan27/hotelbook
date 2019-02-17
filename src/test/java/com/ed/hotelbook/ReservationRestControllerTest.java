package com.ed.hotelbook;

import static org.junit.Assert.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ed.hotelbook.controller.ReservationRestController;
import com.ed.hotelbook.model.Customer;
import com.ed.hotelbook.model.Reservation;
import com.ed.hotelbook.model.ReservationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationRestController.class)
public class ReservationRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ReservationRepository reservationRepository;

	private Customer customer;
	private Reservation reservation;

	@Before
	public void setUp() throws Exception {
		customer = Customer.builder().customerName("Edo").build();
		reservation = Reservation.builder().customer(customer).build();
	}

	@Ignore
	@Test
	public void testGetReservation() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveReservation() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		String reservationAsString = objectMapper
				.writeValueAsString(reservation);

		Mockito.when(reservationRepository.save(reservation))
				.thenReturn(reservation);

		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/reservation")
				.contentType(APPLICATION_JSON).characterEncoding("utf-8")
				.content(reservationAsString)).andExpect(status().isOk());
	}

	@Ignore
	@Test
	public void testPutReservation() {

		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testDeleteReservation() {
		fail("Not yet implemented");
	}

}
