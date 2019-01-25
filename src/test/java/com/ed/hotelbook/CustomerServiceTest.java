package com.ed.hotelbook;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ed.hotelbook.model.Customer;
import com.ed.hotelbook.model.CustomerRepository;

public class CustomerServiceTest {

	@MockBean
	CustomerRepository customerRepository;
	private ArrayList<Customer> customerList;
	
	@Before
	public void setup() {
		Customer first = Customer.builder().customerId(100)
				.customerName("Batman").build();
		Customer second = Customer.builder().customerId(200)
				.customerName("Superman").build();

		customerList = new ArrayList<Customer>();
		customerList.add(first);
		customerList.add(second);
	}
	
	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByCustomerName() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByCustomerId() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveCustomer() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteCustomer() {
		fail("Not yet implemented");
	}

}
