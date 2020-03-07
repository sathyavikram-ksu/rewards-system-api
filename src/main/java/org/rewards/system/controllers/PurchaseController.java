package org.rewards.system.controllers;

import org.rewards.system.models.Purchase;
import org.rewards.system.models.exceptions.ResourceNotFoundException;
import org.rewards.system.repository.CustomerRepository;
import org.rewards.system.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    Purchase newCustomer(@Valid @RequestBody Purchase newPurchase) {
        newPurchase.setPoints(newPurchase.getAmount().longValue());
        //for gold, add 2X points
        return purchaseRepository.save(newPurchase);
    }

    @GetMapping("/{customerId}")
    Iterable<Purchase> allByCustomer(@PathVariable Long customerId) {
        return customerRepository.findById(customerId)
                .map(customer -> {
                    return purchaseRepository.findAllByCustomerOrderByCreatedAtAsc(customer);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
    }

    @DeleteMapping("/{id}")
    void deleteCustomer(@PathVariable Long id) {
        try {
            purchaseRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Purchase", "id", id);
        }
    }
}
