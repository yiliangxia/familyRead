package com.familyRead.admin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.familyRead.model.Customer;
import com.familyRead.util.UUIDGenerator;

/**
 * 后台首页
 * 
 * @author 程高伟
 *
 * @date 2016年10月26日 下午5:40:54
 */

@Controller
public class HomeController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	DecimalFormat df = new DecimalFormat("######0.00");
	
	@RequestMapping(value = "/toLogin", method = RequestMethod.GET)
	public String toLogin(Model model) {
		model.addAttribute("title", "登录页");
		return "admin/login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model,Customer customer) {
		logger.info(customer.getPhone()+"于"+format1.format(new Date())+"登陆");
		model.addAttribute("customer", customer);
		return "redirect:index";
	}
	
	@RequestMapping(value = "/index")
	public String toIndex(Model model,Customer customer) {
		logger.info(customer.getPhone()+"于"+format1.format(new Date())+"进入首页");
		model.addAttribute("customer", customer);
		return "index";
	}
	
	@RequestMapping(value = "/toUpload", method = RequestMethod.GET)
	public String toUpload(Model model) {
		logger.debug("-----后台首页-----");
		model.addAttribute("title", "后台首页");
		return "admin/fileupload";
	}
	
	@RequestMapping(value = "/upload", produces = "text/html;charset=utf-8")  
    @ResponseBody  
    public String uploadFlatness(@RequestParam("fileId") MultipartFile file,  
            HttpServletRequest request) throws IOException {  
		String uploadPath = request.getSession().getServletContext().getRealPath("/")+"assets\\upload\\";
        // 1、解析文件数据，并存入车检数据库  
        InputStream fileInput =file.getInputStream() ;  
        String name = UUIDGenerator.getUUID()+file.getOriginalFilename();
        if(!new File(uploadPath).exists()){
        	new File(uploadPath).mkdirs();
        }
        String filePath = uploadPath+name;
        File ff = new File(filePath);
        file.transferTo(ff);
        fileInput.close();  
        return "{msg:success}";
    }  

}
