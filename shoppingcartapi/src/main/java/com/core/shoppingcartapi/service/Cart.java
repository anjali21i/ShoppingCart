package com.core.shoppingcartapi.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.shoppingcartapi.model.Products;
import com.core.shoppingcartapi.repo.ProductDataRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Cart {

    private Map<Products, Integer> cartItems = new HashMap<>();
    
	@Autowired
    private ProductDataRepo productRepo;
	
	public void addProductToCart(Integer productId, Integer count) {
        Products product = productRepo.findById(productId).orElse(null);
        if (product != null) {
            cartItems.put(product, count);
        }
	}
	
	public Double calculateTotal(Map<Products, Integer> productsCountMap) {
		Double totalPrice= 0.0;
		for (Map.Entry<Products, Integer> entry : productsCountMap.entrySet()) {
			Products key = entry.getKey();
			Integer itemCount = entry.getValue();
			totalPrice+=calculatePerCountPrice(key, itemCount);
			
		}
		return totalPrice;
	}
	
	public Double calculatePerCountPrice(Products products, int count) {
		Double totalPrice= 0.0;
		totalPrice = products.getPrice() * count;
		return totalPrice;
	}
       
}
