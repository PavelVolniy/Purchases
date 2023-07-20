package com.chetverik.repositories;

import com.chetverik.domain.entityes.Branch;
import org.springframework.data.repository.CrudRepository;

public interface BranchRepo extends CrudRepository<Branch, Long> {
    Branch findByName(String name);
}
