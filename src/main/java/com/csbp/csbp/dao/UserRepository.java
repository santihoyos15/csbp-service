package com.csbp.csbp.dao;

import com.csbp.csbp.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findByDni(String dni);
}
