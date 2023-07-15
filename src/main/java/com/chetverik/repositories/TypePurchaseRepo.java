package com.chetverik.repositories;

import com.chetverik.domain.contract.TypeOfPurchase;
import org.springframework.data.repository.CrudRepository;

public interface TypePurchaseRepo extends CrudRepository<TypeOfPurchase, Long> {
    TypePurchaseRepo findByNameTypeOfPurchase(String name);
}
