package com.chetverik.repositories;

import com.chetverik.domain.entityes.Supplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {
    Supplier findBynameSupplier(String nameSupplier);
    Supplier findByinn(Long inn);

    @Query("from Supplier s where s.nameSupplier like %:nameSupplier%")
    List<Supplier> getListSuppliersConsistValue(@Param("nameSupplier") String nameSupplier);
}
