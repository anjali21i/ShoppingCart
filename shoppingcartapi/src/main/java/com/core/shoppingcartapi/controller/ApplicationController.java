package com.core.shoppingcartapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.core.shoppingcartapi.model.Customer;
import com.core.shoppingcartapi.model.Products;
import com.core.shoppingcartapi.service.Cart;
import com.core.shoppingcartapi.service.ServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

//@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/api")
public class ApplicationController {

	@Autowired
	ServiceImpl serviceImpl;
	
	@Autowired
	Cart cart;
	
	
//	@GetMapping("/getUserData")
//	public Customer getUserData(HttpServletRequest httpRequest, @RequestParam String uid) {
//		return new Customer();
//	}
	
	
	@GetMapping("/getAllProductData")
	public List<Products> getAllProductData(HttpServletRequest httpRequest, HttpServletResponse response) {
		return serviceImpl.getAllProductData();
	}

	@PostMapping("/addProduct")
	public boolean addProduct(@RequestBody Products prod, Integer count) {
		if(prod!=null && count>0) {
			
			return true;
		}
		return false;
	}
	
//	@PostMapping("/addUser")
//	public boolean addUser(@RequestBody Customer user) {
//		return false;
//	}
	
//	@PostMapping("/updateProductCount")
//	public boolean updateProductCount(@RequestParam int count,@RequestParam int pid ) {
//		if(count>0) {
//			return serviceImpl.updateProductCount(count, pid);
//		}
//		return false;
//	}
	
	@PostMapping("/submitCart")
	public String submitProductToCart(@RequestParam Map<Products, Integer> productsDetail, Customer userDetail) {
		
		if(userDetail==null && !StringUtils.hasLength(userDetail.getEmail())) {
			return "Please provide user details";
		}
		if(productsDetail.isEmpty()) {
			return "Nothing added to proceed";
		}
		serviceImpl.submitAllData(productsDetail, userDetail);
		
		return null;
	}
	
	@PostMapping("/calculateTotal")
	public Double calculateTotal(@RequestParam Map<Products, Integer> products) {
		if(products.isEmpty()) {
			log.error("Nothing to calculate");
		}
		return cart.calculateTotal(products);
	}
	

}
