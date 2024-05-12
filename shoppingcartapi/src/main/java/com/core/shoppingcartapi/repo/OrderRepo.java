package com.core.shoppingcartapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.shoppingcartapi.model.Order;

public interface OrderRepo extends JpaRepository<Order, Integer>{

}
