package com.elderpereira.customerservice.repository;

import com.elderpereira.customerservice.domain.Customer;
import com.elderpereira.customerservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
