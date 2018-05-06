package com.familyRead.controller.admin;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.familyRead.model.Customer;

/**
 * @author xyl
 *
 */

@Controller
public class HomeController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	DecimalFormat df = new DecimalFormat("######0.00");
	
	@RequestMapping(value = "/")
	public String index(Model model) {
		return "redirect:index";
	}
	
	@RequestMapping(value = "/toLogin", method = RequestMethod.GET)
	public String toLogin(Model model) {
		return "admin/login";
	}
	
	@RequestMapping(value = "/index")
	public String toIndex(Model model,Customer customer) {
		logger.info(customer.getPhone()+"于"+format1.format(new Date())+"进入首页");
		model.addAttribute("customer", customer);
		return "index";
	}
	
}
