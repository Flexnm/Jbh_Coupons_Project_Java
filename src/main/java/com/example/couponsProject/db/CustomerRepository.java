package com.example.couponsProject.db;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.couponsProject.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Optional<Customer> findByEmailAndPassword(String email, String password);

	Optional<Customer> findByEmail(String email);

}
