package com.olejnik_macek.Olejnik_Macek.controller;

import com.olejnik_macek.Olejnik_Macek.model.Customer;
import com.olejnik_macek.Olejnik_Macek.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    // get 500
    @GetMapping("/customer/byOffSet/{offSet}")
    public List<Customer> getCustomersWithOffSet(@PathVariable(value = "offSet") Integer offSet) {
        Pageable pageable = PageRequest.of(offSet,500, Sort.by("lastName").and(Sort.by("firstName")));

        Page<Customer> employees = customerRepository.findAll(pageable);

        List<Customer> listOfCustomers = employees.getContent();

        return listOfCustomers;
    }

    // create new customer
    @PostMapping("/customer")
    public Boolean createEmployee(@Valid @RequestBody Customer customer) {
        if(customerRepository.existsByCustomerID(customer.getCustomerID())) {
            return false;
        }
        customerRepository.save(customer);

        return true;
    }

    // get customer by ID
    @GetMapping("/customer/searchByID/{customerID}/{offSet}")
    public List<Customer> getCustomerByID(@PathVariable(value = "customerID") String customerID,
                                                        @PathVariable(value = "offSet") Integer offSet) {
        Pageable pageable = PageRequest.of(offSet,500);

        return customerRepository.findAllByCustomerIDContainingOrderByLastNameAscFirstNameAsc(customerID,pageable);
    }

    // update
    @PutMapping("/customer")
    public Boolean updateCustomer(@Valid @RequestBody Customer customerDetail) {

        Customer customer = customerRepository.findById(customerDetail.getCustomerID())
                .orElse(null);

        if(customer == null) {
            return false;
        }

        customer.setFirstName(customerDetail.getFirstName());
        customer.setLastName(customerDetail.getLastName());
        customer.setAddress(customerDetail.getAddress());
        customer.setBankAccount(customerDetail.getBankAccount());
        customer.setPhone(customerDetail.getPhone());

        customerRepository.save(customer);

        return true;
    }

    // delete
    @DeleteMapping("/customer/{customerID}")
    public boolean deleteCustomer(@PathVariable(value = "customerID") String customerID) {

        Customer customer = customerRepository.findById(customerID).orElse(null);

        if(customer == null) {
            return false;
        } else {
            customerRepository.delete(customer);
            return true;
        }
    }
}
