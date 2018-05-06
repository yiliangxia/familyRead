package com.familyRead.controller.admin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.familyRead.model.Customer;
import com.familyRead.model.FileInfo;
import com.familyRead.service.impl.FileServiceImpl;
import com.familyRead.util.Page;
import com.google.gson.JsonObject;

/**
 * 
 * @author xyl
 *
 */

@Controller
public class FileManagerController {
	
	@Autowired FileServiceImpl fileService;
	
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
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		String uploadPath = request.getSession().getServletContext().getRealPath("/")+"assets/upload";
        // 1、解析文件数据，并存入车检数据库  
        InputStream fileInput =file.getInputStream() ;  
        String name = file.getOriginalFilename();
        File f = new File(uploadPath);
        if(!f.exists()){
        	f.setWritable(true, false);
        	f.mkdirs();
        }
        String filePath = uploadPath+File.separator+name;
        File ff = new File(filePath);
        if(ff.exists()){
        	ff.delete();
        }
        file.transferTo(ff);
        fileInput.close();  
        
        FileInfo fileInfo = new FileInfo();
        if(name.endsWith("jpg")||name.endsWith("png")||name.endsWith("gif")){
        	fileInfo.setFileType(1);
        }
        if(name.endsWith("pdf")){
        	fileInfo.setFileType(2);
        }
        if(name.endsWith("mp3")||name.endsWith("wmv")||name.endsWith("rmvb")||name.endsWith("avi")||name.endsWith("wmv")){
        	fileInfo.setFileType(3);
        }
        fileInfo.setFileName(name);
        fileInfo.setCreateTime(new Date());
        fileInfo.setCreateBy(customer.getUserName());
        try {
			fileService.insertSelective(fileInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return ("[{\"success\":"+ true +"}]").toString(); 
    }  
	
	@RequestMapping(value = "/toImgManage", method = RequestMethod.GET)
	public String toImgManage(Model model,FileInfo fileInfo,String pageNo,String pageSize) {
		Page<FileInfo> page = new Page<FileInfo>();
		Integer _pageSize = 10;
		if(!StringUtils.isBlank(pageSize)){
			_pageSize=Integer.parseInt(pageSize);
		}
		page.setPageSize(_pageSize);
		int _pageNo = 1;
		if(fileInfo.getFileType()==null||"".equals(fileInfo.getFileType())){
			fileInfo.setFileType(1);
		}
		if (pageNo != null && !"".equals(pageNo)) {
			try {
				_pageNo = Integer.parseInt(pageNo);
				page.setPageNo(_pageNo);
			} catch (Exception e) {
				logger.info("页码转换失败{}", pageNo);
			}
		}
		page.setParameter(fileInfo);
		page.setResult(fileService.selectFileInfoPage(page));
		page.setTotalCount(fileService.selectFileInfoCount(page));
		
		
		logger.debug("-----img管理页-----");
		model.addAttribute("page", page);
		return "admin/imgManage";
	}
	
	@RequestMapping(value = "/toDocManage", method = RequestMethod.GET)
	public String toDocManage(Model model,FileInfo fileInfo,String pageNo,String pageSize) {
		Page<FileInfo> page = new Page<FileInfo>();
		Integer _pageSize = 10;
		if(!StringUtils.isBlank(pageSize)){
			_pageSize=Integer.parseInt(pageSize);
		}
		page.setPageSize(_pageSize);
		int _pageNo = 1;
		if(fileInfo.getFileType()==null||"".equals(fileInfo.getFileType())){
			fileInfo.setFileType(2);
		}
		if (pageNo != null && !"".equals(pageNo)) {
			try {
				_pageNo = Integer.parseInt(pageNo);
				page.setPageNo(_pageNo);
			} catch (Exception e) {
				logger.info("页码转换失败{}", pageNo);
			}
		}
		page.setParameter(fileInfo);
		page.setResult(fileService.selectFileInfoPage(page));
		page.setTotalCount(fileService.selectFileInfoCount(page));
		
		
		logger.debug("-----文档管理页-----");
		model.addAttribute("page", page);
		return "admin/docManage";
	}
	
	@RequestMapping(value = "/toVedioManage", method = RequestMethod.GET)
	public String toVedioManage(Model model,FileInfo fileInfo,String pageNo,String pageSize) {
		Page<FileInfo> page = new Page<FileInfo>();
		Integer _pageSize = 10;
		if(!StringUtils.isBlank(pageSize)){
			_pageSize=Integer.parseInt(pageSize);
		}
		page.setPageSize(_pageSize);
		int _pageNo = 1;
		if(fileInfo.getFileType()==null||"".equals(fileInfo.getFileType())){
			fileInfo.setFileType(3);
		}
		if (pageNo != null && !"".equals(pageNo)) {
			try {
				_pageNo = Integer.parseInt(pageNo);
				page.setPageNo(_pageNo);
			} catch (Exception e) {
				logger.info("页码转换失败{}", pageNo);
			}
		}
		page.setParameter(fileInfo);
		page.setResult(fileService.selectFileInfoPage(page));
		page.setTotalCount(fileService.selectFileInfoCount(page));
		
		
		logger.debug("-----音频管理页-----");
		model.addAttribute("page", page);
		return "admin/vedioManage";
	}

}
