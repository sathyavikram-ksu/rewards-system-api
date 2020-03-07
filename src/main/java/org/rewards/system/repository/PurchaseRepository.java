package org.rewards.system.repository;

import org.rewards.system.models.Customer;
import org.rewards.system.models.Purchase;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    Iterable<Purchase> findAllByCustomerOrderByCreatedAtAsc(Customer customer);
}
