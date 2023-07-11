package com.chetverik.repositories;

import com.chetverik.domain.contract.Branch;
import org.springframework.data.repository.CrudRepository;

public interface BranchRepo extends CrudRepository<Branch, Long> {
    Branch findByName(String name);
}
