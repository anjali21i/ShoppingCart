package com.core.shoppingcartapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.core.shoppingcartapi.model.Products;

public interface ProductDataRepo extends JpaRepository<Products, Integer> {

	int findCountByPid(int pid);

	@Query("update products set count = :count where pid=:pid")
	void updateProductsByCount(int count, int pid);

}
