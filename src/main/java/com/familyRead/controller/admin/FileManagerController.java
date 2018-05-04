package com.familyRead.controller.admin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

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

import com.familyRead.util.UUIDGenerator;
import com.google.gson.JsonObject;

/**
 * 
 * @author xyl
 *
 */

@Controller
public class FileManagerController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	DecimalFormat df = new DecimalFormat("######0.00");
	JsonObject jo = new JsonObject();
	
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
        String name = file.getOriginalFilename();
        if(!new File(uploadPath).exists()){
        	new File(uploadPath).mkdirs();
        }
        String filePath = uploadPath+name;
        File ff = new File(filePath);
        if(ff.exists()){
        	ff.delete();
        }
        file.transferTo(ff);
        fileInput.close();  
        return ("[{\"success\":"+ true +"}]").toString(); 
    }  
	
	@RequestMapping(value = "/toImgManage", method = RequestMethod.GET)
	public String toImgManage(Model model) {
		logger.debug("-----后台首页-----");
		model.addAttribute("title", "后台首页");
		return "admin/imgManage";
	}
	
	@RequestMapping(value = "/toVedioManage", method = RequestMethod.GET)
	public String toVedioManage(Model model) {
		logger.debug("-----后台首页-----");
		model.addAttribute("title", "后台首页");
		return "admin/vedioManage";
	}
	
	@RequestMapping(value = "/toDocManage", method = RequestMethod.GET)
	public String toDocManage(Model model) {
		logger.debug("-----后台首页-----");
		model.addAttribute("title", "后台首页");
		return "admin/docManage";
	}

}
