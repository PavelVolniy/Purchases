package com.chetverik.repositories;

import com.chetverik.domain.contract.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {
}
