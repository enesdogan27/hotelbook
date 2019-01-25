package com.ed.hotelbook.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public Optional<Customer> getCustomerByCustomerId(
			@PathVariable("id") long customerId) {
		return customerService.findByCustomerId(customerId);
	}

	@PostMapping
	public Customer addCustomer(@RequestBody Customer customer) {
		LOG.info("Customer add: "+customer.toString());
		return customerService.saveCustomer(customer);
	}

	@PutMapping("/{id}")
	public Customer updateCustomer(@PathVariable("id") long customerId,
			@RequestBody Customer customer) {
		return customerService.updateCustomer(customerId, customer);
	}

	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable("id") long customerId) {
		customerService.deleteCustomer(customerId);
	}
}
