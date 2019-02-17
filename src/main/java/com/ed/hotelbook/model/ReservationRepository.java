package com.ed.hotelbook.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository
		extends JpaRepository<Reservation, Long> {
	List<Reservation> findAll();

	Optional<Reservation> findByReservationId(long reservationId);
	
	//@Query("SELECT * FROM Reservation r WHERE r.customer.customerid = :customerId")
	List<Reservation> findByCustomer_CustomerId(long customerId);
}
