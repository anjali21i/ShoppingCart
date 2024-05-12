package com.core.shoppingcartapi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.shoppingcartapi.model.Customer;
import com.core.shoppingcartapi.model.Order;
import com.core.shoppingcartapi.model.Products;
import com.core.shoppingcartapi.repo.CustomerRepo;
import com.core.shoppingcartapi.repo.OrderRepo;
import com.core.shoppingcartapi.repo.ProductDataRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceImpl {

	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	CustomerRepo userRepo;
	
	@Autowired
	ProductDataRepo productRepo;
	
	@Autowired
	Cart cart;
		
	public List<Products> getAllProductData() {
		List<Products> dataList = new ArrayList<>();
		try {
			dataList = productRepo.findAll();
		} catch (Exception e) {
			log.error("Error in db call");
			e.printStackTrace();
		}
		return dataList;
	}

	public boolean updateProductCount(int count, int pid) {
		
		int availableCount = productRepo.findCountByPid(pid);
		if(availableCount >0 && count < availableCount) {
			availableCount = availableCount-count;
			productRepo.updateProductsByCount(availableCount, pid);
		} else {
			 log.error("unable to add product");
		}
		return false;
	}

	public boolean submitAllData(Map<Products, Integer> products, Customer userDetail) {
		boolean isUpdatedCount= false;
//		List<Integer> pids = new ArrayList<Integer>();
//		for (Products product : products) {
//			
//			isUpdatedCount = updateProductCount(product.getCount(), product.getPid());
//			
//		}
		
		Customer user = userRepo.save(userDetail);
		Order order = new Order();
		order.setCustomer(user);
		order.setOdate(new Date());
		order.setPDetailList(null);
		order = orderRepo.save(order);
		userRepo.updateCustomerData(order.getOid(), user.getUid());
		
		return isUpdatedCount;
	}
	
	

	
	
	
}
