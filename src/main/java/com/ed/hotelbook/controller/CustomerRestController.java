package com.ed.hotelbook.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ed.hotelbook.model.Customer;
import com.ed.hotelbook.model.CustomerRepository;

@RestController
@RequestMapping("api/customer")
class CustomerRestController {

	CustomerRepository customerRepository;
	Logger LOG = LoggerFactory.getLogger(CustomerRestController.class);

	public CustomerRestController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@GetMapping
	public List<Customer> getCustomers(
			@RequestParam(name = "customerName", required = false, defaultValue = "")
			Optional<String> customerName) {
		
		return customerRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(
			@PathVariable("id") Optional<Customer> customer) {
		if (!customer.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(customer.get(), HttpStatus.OK);
	}

	@PostMapping
	public Customer addCustomer(@RequestBody Customer customer) {
		LOG.info(customer.toString());
		return customerRepository.save(customer);
	}
}
