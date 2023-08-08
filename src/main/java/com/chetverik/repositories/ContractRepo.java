package com.chetverik.repositories;

import com.chetverik.domain.contract.Contract;
import com.chetverik.domain.entityes.Branch;
import com.chetverik.domain.entityes.Supplier;
import com.chetverik.domain.entityes.TypeOfPurchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractRepo extends CrudRepository<Contract, Long> {

    Contract findFirstById(Long aLong);

    Contract findByNameOfContract(String findByNameOfContract);

    @Query("from Contract c where c.branch in (:branch) " +
            "and c.nameOfContract like %:nameOfContract% " +
            "and c.typeOfPurchase in (:typeOfPurchase) " +
            "and c.numberOfContract like %:numberOfContract% " +
            "and c.supplier in (:supplier)" +
            "and c.sum between :sumMore and :sumLess")
    List<Contract> listContractsByParam(
            @Param("branch") List<Branch> branch,
            @Param("nameOfContract") String nameOfContract,
            @Param("typeOfPurchase") List<TypeOfPurchase> typeOfPurchase,
            @Param("numberOfContract") String numberOfContract,
            @Param("supplier") List<Supplier> supplier,
            @Param("sumMore") Double sumMore,
            @Param("sumLess") Double sumLess
    );

}
