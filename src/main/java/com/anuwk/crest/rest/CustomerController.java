package com.anuwk.crest.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anuwk.crest.entity.Customer;
import com.anuwk.crest.exception.CustomerNotFoundException;
import com.anuwk.crest.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		return customerService.getCustomers();
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomers(@PathVariable int customerId){
		Customer theCustomer =  customerService.getCustomer(customerId);
		
		if(theCustomer == null) {
			throw new CustomerNotFoundException("Customer ID not found : " + customerId);
		}
		
		return theCustomer;
	}
	
	@PostMapping("/customers")
	public Customer addCustomers(@RequestBody Customer theCustomer){
		theCustomer.setId(0);
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	@PutMapping("/customers")
	public Customer updateCustomers(@RequestBody Customer theCustomer){
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}
	
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomers(@PathVariable int customerId){
		Customer tempCustomer =  customerService.getCustomer(customerId);
		
		if(tempCustomer == null) {
			throw new CustomerNotFoundException("Customer ID not found : " + customerId);
		}
		customerService.deleteCustomer(customerId);
		return "Customer ID:"+customerId+"  has been deleted ";
	}
	
	
}
