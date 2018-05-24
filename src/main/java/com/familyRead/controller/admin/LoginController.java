package com.familyRead.controller.admin;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.familyRead.model.Customer;
import com.familyRead.service.impl.CustomerServiceImpl;

/**
 * @author xyl
 *
 */

@Controller
public class LoginController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	DecimalFormat df = new DecimalFormat("######0.00");
	
	@Autowired CustomerServiceImpl customerService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request,Model model,Customer customer) {
		logger.info(customer.getPhone()+"于"+format1.format(new Date())+"登陆");
		List<Customer> listResult = customerService.selectLoginCustomer(customer);
		if(listResult!=null&&listResult.size()>0){
			request.getSession().setAttribute("customer", listResult.get(0));
		}
		return "redirect:index";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,Model model,Customer customer) {
		logger.info(customer.getPhone()+"于"+format1.format(new Date())+"登出");
		request.getSession().removeAttribute("customer");
		return "redirect:index";
	}
	
}
