package com.familyRead.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familyRead.dao.CustomerMapper;
import com.familyRead.model.Customer;


@Service
public class CustomerServiceImpl {
	
	@Autowired CustomerMapper customerMapper;
	
	public List<Customer> selectLoginCustomer(Customer customer){
		
		return customerMapper.selectCustomerByPhone(customer);
	}
}
