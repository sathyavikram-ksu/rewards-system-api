package org.rewards.system.repository;

import org.rewards.system.models.Customer;
import org.rewards.system.models.Purchase;
import org.rewards.system.models.PurchaseType;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    Iterable<Purchase> findAllByCustomerOrderByCreatedAtDesc(Customer customer);

    Iterable<Purchase> findAllByPickUpDateAndPurchaseType(Long pickUpDate, PurchaseType purchaseType);

    Iterable<Purchase> findAllByCreatedAtBetweenOrderByCreatedAtDesc(Long createdAtStart, Long createdAtEnd);
}
