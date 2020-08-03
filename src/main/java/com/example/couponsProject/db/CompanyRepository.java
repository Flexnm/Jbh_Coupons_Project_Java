package com.example.couponsProject.db;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.couponsProject.beans.Company;


public interface CompanyRepository extends JpaRepository<Company, Integer> {

	Optional<Company> findCompanyByEmailAndPassword(String email, String password);

	
	Optional<Company> findCompanyByNameOrEmail(String name, String email);

}
