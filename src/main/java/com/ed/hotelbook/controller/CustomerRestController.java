package com.ed.hotelbook.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ed.hotelbook.model.Customer;
import com.ed.hotelbook.service.CustomerService;

@RestController
@RequestMapping("api/customer")
public class CustomerRestController {

	CustomerService customerService;
	Logger LOG = LoggerFactory.getLogger(CustomerRestController.class);

	public CustomerRestController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	public List<Customer> getCustomers() {
		return customerService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Customer> getCustomer(
			@PathVariable("id") String customerId) {
		return customerService.findByCustomerId(customerId);
	}

	@PostMapping
	public Customer addCustomer(@RequestBody Customer customer) {
		LOG.info(customer.toString());
		return customerService.saveCustomer(customer);
	}
}
