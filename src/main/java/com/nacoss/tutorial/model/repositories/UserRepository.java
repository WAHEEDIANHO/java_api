package com.nacoss.tutorial.model.repositories;

import com.nacoss.tutorial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

//    List<User> findById(Long id)
}
