package com.core.shoppingcartapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.core.shoppingcartapi.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Integer>{

	@Query("update Customer set oid = :oid where uid=:uid")
	void updateCustomerData(String oid, Integer uid);
	
	

	
}
