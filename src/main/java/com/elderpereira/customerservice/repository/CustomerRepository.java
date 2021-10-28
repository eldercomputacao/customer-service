package com.elderpereira.customerservice.repository;

import com.elderpereira.customerservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCpf(String cpf);

    Optional<Customer> findByPhone(String phone);

    Optional<Customer> findByEmail(String email);

    @Modifying
    @Query("UPDATE Customer p SET p.email = :email WHERE p.id = :id")
    void updateEmail(@Param("id") Long id, @Param("email") String email);

    @Modifying
    @Query("UPDATE Customer p SET p.phone = :phone WHERE p.id = :id")
    void updatePhone(@Param("id") Long id, @Param("phone") String phone);

    @Modifying
    @Query("UPDATE Customer p SET p.cpf = :cpf WHERE p.id = :id")
    void updateCpf(@Param("id") Long id, @Param("cpf") String cpf);

}
