package com.chetverik.repositories;


import com.chetverik.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findAllById(Long userId);
}
