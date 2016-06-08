package se.panok.spike.protobuf.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.panok.spike.protobuf.model.CustomerProtos.Customer;
import se.panok.spike.protobuf.repo.CustomerRepository;

public @RestController class CustomerRestController {

	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping("/customers/{id}")
	Customer customer(@PathVariable Integer id) {
		return this.customerRepository.findById(id);
	}
}
