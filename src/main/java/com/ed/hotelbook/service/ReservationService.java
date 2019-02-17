package com.ed.hotelbook.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ed.hotelbook.config.CustomerNotFoundException;
import com.ed.hotelbook.model.Customer;
import com.ed.hotelbook.model.CustomerRepository;
import com.ed.hotelbook.model.Reservation;
import com.ed.hotelbook.model.ReservationRepository;

@Service
public class ReservationService {

	Logger LOG = LoggerFactory.getLogger(ReservationService.class);

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	public List<Reservation> findByCustomerId(long customerId) {
		return reservationRepository.findByCustomer_CustomerId(customerId);
	}

	public Optional<Reservation> findById(long id) {
		return reservationRepository.findById(id);
	}

	public Reservation saveReservation(Reservation reservation, long customerId) {
		return customerRepository.findByCustomerId(customerId).map(customer -> {
			reservation.setCustomer(customer);
			return reservationRepository.save(reservation);
		}).orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
	}

	public Reservation updateReservation(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	public void deleteReservation(long reservationId) {
		reservationRepository.findById(reservationId)
				.ifPresent(reservation -> reservationRepository.delete(reservation));
	}
}
