package com.ed.hotelbook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ed.hotelbook.model.Customer;
import com.ed.hotelbook.model.CustomerRepository;

@Service
public class CustomerService {

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

	public Optional<Customer> findByCustomerId(String customerId) {
		return customerRepository.findByCustomerId(customerId);
	}

	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
}
