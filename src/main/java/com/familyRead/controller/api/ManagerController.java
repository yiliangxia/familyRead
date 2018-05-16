package com.familyRead.controller.api;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.familyRead.service.impl.FileServiceImpl;
import com.google.gson.JsonObject;

/**
 * 
 * @author xyl
 *
 */

@Controller
public class ManagerController {
	
	@Autowired FileServiceImpl fileService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	DecimalFormat df = new DecimalFormat("######0.00");
	JsonObject jo = new JsonObject();
	
	@RequestMapping(value = "/getFile", method = RequestMethod.GET)
	public String toUpload(Model model) {
		logger.debug("-----后台首页-----");
		model.addAttribute("title", "后台首页");
		return "admin/fileupload";
	}
	
}
