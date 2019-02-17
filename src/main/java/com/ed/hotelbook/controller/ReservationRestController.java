package com.ed.hotelbook.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ed.hotelbook.config.CustomerNotFoundException;
import com.ed.hotelbook.model.CustomerRepository;
import com.ed.hotelbook.model.Reservation;
import com.ed.hotelbook.model.ReservationRepository;
import com.ed.hotelbook.service.ReservationService;

@RestController
@RequestMapping(path = "/api/reservation")
public class ReservationRestController {

	private ReservationService reservationService;

	public ReservationRestController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping
	public List<Reservation> getReservationByCustomer(@Param("customerId") Optional<Long> customerId) {
		if (customerId.isPresent())
			return reservationService.findByCustomerId(customerId.get());
		else
			return reservationService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Reservation> getReservation(@PathVariable long reservationId) {
		return reservationService.findById(reservationId);
	}

	@PostMapping
	public Reservation postReservation(@Param("customerId") @NonNull long customerId,
			@RequestBody Reservation reservation) {
		return reservationService.saveReservation(reservation, customerId);
	}

	@PutMapping("/{id}")
	public Reservation putReservation(@PathVariable long reservationId, @RequestBody Reservation reservation) {
		return reservationService.updateReservation(reservation);
	}

	@DeleteMapping("/{id}")
	public void deleteReservation(@PathVariable long reservationId, @RequestBody Reservation reservation) {
		reservationService.deleteReservation(reservationId);
	}
}
