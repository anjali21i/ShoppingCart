package com.core.shoppingcartapi.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String oid;
	private Date odate;
	
	@ManyToOne
	@JoinColumn(name = "uid", nullable = false)
	private Customer customer;
	
	@OneToMany(mappedBy = "pid", cascade = CascadeType.ALL)
	private Map<Products,Integer> pDetailList;
	
}
