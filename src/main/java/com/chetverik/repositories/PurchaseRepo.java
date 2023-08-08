package com.chetverik.repositories;

import com.chetverik.domain.purchase.Purchase;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepo extends CrudRepository<Purchase, Long> {
    Purchase findByNamePurchase(String namePurchase);
}
