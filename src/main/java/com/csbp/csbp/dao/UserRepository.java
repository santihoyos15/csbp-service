package com.csbp.csbp.dao;

import com.csbp.csbp.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
    List<User> findAllByRole(Integer role);
}
