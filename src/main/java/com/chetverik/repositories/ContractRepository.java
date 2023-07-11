package com.chetverik.repositories;

import com.chetverik.domain.contract.Contract;
import org.springframework.data.repository.CrudRepository;

public interface ContractRepository extends CrudRepository<Contract, Long> {
}
