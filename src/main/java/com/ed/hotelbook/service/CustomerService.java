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

@Service
public class CustomerService {

	Logger LOG = LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	private CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	public Optional<List<Customer>> findByCustomerName(String customerName) {
		return customerRepository.findByCustomerName(customerName);
	}

	public Optional<Customer> findByCustomerId(long customerId) {
		Customer customer = customerRepository.findByCustomerId(customerId)
				.orElseThrow(() -> new CustomerNotFoundException(
						"Customer Not Found"));
		return Optional.of(customer);
	}

	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer deleteCustomer(long customerId) {
		Customer customer = customerRepository.findByCustomerId(customerId)
				.orElseThrow(() -> new CustomerNotFoundException(
						"Customer Not Found"));

		customerRepository.delete(customer);

		return customer;

	}

	public Customer updateCustomer(long customerId, Customer customer) {
		if (customerId != customer.getCustomerId())
			throw new RuntimeException("Id mismatch error");

		LOG.info("Customer update from: " + this.findByCustomerId(customerId).get());
		LOG.info("Customer update to: "+customer.toString());
		
		return customerRepository.save(customer);
	}
}
