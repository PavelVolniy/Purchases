package com.chetverik.repositories;

import com.chetverik.domain.Contract;
import org.springframework.data.repository.CrudRepository;

public interface ContractRepository extends CrudRepository<Contract, Long> {
}
