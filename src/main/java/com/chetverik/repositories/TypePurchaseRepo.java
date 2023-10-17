package com.chetverik.repositories;

import com.chetverik.domain.entityes.TypeOfPurchase;
import org.springframework.data.repository.CrudRepository;

public interface TypePurchaseRepo extends CrudRepository<TypeOfPurchase, Long> {
    TypeOfPurchase findByNameTypeOfPurchase(String name);
}
