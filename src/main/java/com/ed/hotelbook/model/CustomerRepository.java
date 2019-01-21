package com.ed.hotelbook.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository< Customer, Long> {

	List<Customer> findAll();
	Optional<List<Customer>> findByCustomerName(String customerName);
	Optional<Customer> findById(String id);
}
