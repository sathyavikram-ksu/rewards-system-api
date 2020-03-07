package org.rewards.system.controllers;

import org.rewards.system.models.Customer;
import org.rewards.system.models.exceptions.ResourceNotFoundException;
import org.rewards.system.repository.CustomerRepository;
import org.rewards.system.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    Customer newCustomer(@Valid @RequestBody Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    @GetMapping("")
    Iterable<Customer> all() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Customer> byId(@PathVariable Long id) {
        return customerRepository.findById(id);
    }

    @PutMapping("/{id}")
    Customer updateCustomer(@RequestBody Customer updatedCustomer, @PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.update(updatedCustomer);
                    return customerRepository.save(customer);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
    }

    @DeleteMapping("/{id}")
    void deleteCustomer(@PathVariable Long id) {
        try {
            customerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Customer", "id", id);
        }
    }
}
