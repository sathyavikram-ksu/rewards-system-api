package org.rewards.system.controllers;

import org.rewards.system.models.Purchase;
import org.rewards.system.models.PurchaseType;
import org.rewards.system.models.exceptions.ResourceNotFoundException;
import org.rewards.system.repository.CustomerRepository;
import org.rewards.system.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
        return purchaseRepository.save(newPurchase);
    }

    @GetMapping("/{customerId}")
    Iterable<Purchase> allByCustomer(@PathVariable Long customerId) {
        return customerRepository.findById(customerId)
                .map(customer -> {
                    return purchaseRepository.findAllByCustomerOrderByCreatedAtDesc(customer);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
    }

    @GetMapping("/bydate/{dateLong}")
    Iterable<Purchase> allByDate(@PathVariable Long dateLong) {
        return purchaseRepository
                .findAllByCreatedAtBetweenOrderByCreatedAtDesc(dateLong, dateLong + 86400000);
    }

    @GetMapping("/available-slots/{pickUpDate}")
    Set<Integer> getAvailableSlots(@PathVariable Long pickUpDate) {
        Integer pickUpSlots[] = {1, 2, 3, 4, 5};
        Set<Integer> pickUpSlotsSet = new HashSet<>(Arrays.asList(pickUpSlots));
        Iterable<Purchase> purchaseList = purchaseRepository.
                findAllByPickUpDateAndPurchaseType(pickUpDate, PurchaseType.PRE_ORDER);
        for (Purchase purchase : purchaseList) {
            pickUpSlotsSet.remove(purchase.getPickUpSlot());
        }
        return pickUpSlotsSet;
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
