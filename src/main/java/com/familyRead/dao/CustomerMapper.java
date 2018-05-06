package com.familyRead.dao;

import java.util.List;

import com.familyRead.model.Customer;

import tk.mybatis.mapper.common.Mapper;


public interface CustomerMapper extends Mapper<Customer>{

	List<Customer> selectCustomerByPhone(Customer customer);
	
}