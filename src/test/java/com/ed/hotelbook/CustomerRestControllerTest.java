package com.ed.hotelbook;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ed.hotelbook.controller.CustomerRestController;
import com.ed.hotelbook.model.Customer;
import com.ed.hotelbook.model.CustomerRepository;
import com.ed.hotelbook.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerRestController.class)
public class CustomerRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CustomerService customerService;
	List<Customer> customerList;

	@Before
	public void setUp() {
		Customer first = Customer.builder().customerId(100)
				.customerName("Batman").build();
		Customer second = Customer.builder().customerId(200)
				.customerName("Superman").build();

		customerList = new ArrayList<Customer>();
		customerList.add(first);
		customerList.add(second);
	}

	@Test
	public void getAllCustomers() throws Exception {
		when(customerService.findAll()).thenReturn(customerList);

		mvc.perform(get("/api/customer").contentType(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	public void getCustomerById() throws Exception {
		Customer customer = Customer.builder().customerId(100)
				.customerName("Batman").build();

		when(customerService.findByCustomerId(100))
				.thenReturn(Optional.of(customer));

//		MvcResult result = 
		mvc.perform(get("/api/customer/" + customer.getCustomerId())
				.contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.customerID", is("100")))
				.andExpect(jsonPath("$.customerName", is("Batman")))
				.andReturn();

//		System.out.println(result.getResponse().getContentAsString());
	}
}
