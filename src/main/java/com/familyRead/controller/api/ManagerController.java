package com.familyRead.controller.api;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familyRead.dao.BannerMapper;
import com.familyRead.dao.GroupsMapper;
import com.familyRead.dao.PictureBookMapper;
import com.familyRead.model.Banner;
import com.familyRead.model.Groups;
import com.familyRead.model.PictureBook;
import com.familyRead.service.impl.FileServiceImpl;
import com.google.gson.JsonObject;

/**
 * 
 * @author xyl
 *
 */

@Controller
@RequestMapping("/api")
public class ManagerController {
	
	@Autowired FileServiceImpl fileService;
	@Autowired BannerMapper bannerMapper;
	
	@Autowired GroupsMapper groupsMapper;
	
	@Autowired PictureBookMapper pictureMapper;
	
	Integer successCode = 200;
	String successMsg = "OK";
	Integer failureCode = -1;
	String failureMsg = "failure";
	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	DecimalFormat df = new DecimalFormat("######0.00");
	JsonObject jo = new JsonObject();
	
	/**
	 * 首页返回数据
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/indexData")
	@ResponseBody
	public Map<String,Object> indexData(Model model) {
		Map<String,Object> listResult = new HashMap<String,Object>();
		List<Banner> newsList = null;
		List<Banner> bannerList = null;
		try {
			/**
			 * banner图
			 */
			Integer bannerType = 4;
			bannerList = bannerMapper.selectBannerByBannerType(bannerType);
			/**
			 * news图
			 */
			bannerType = 4;
			newsList = bannerMapper.selectNewsByBannerType(bannerType);
			listResult.put("rpco", successCode);
			listResult.put("msg", successMsg);
			listResult.put("banners", bannerList);
			listResult.put("news", newsList);
		} catch (Exception e) {
			listResult.put("rpco", failureCode);
			listResult.put("msg", "加载banner失败");
			e.printStackTrace();
		}
		
		
		return listResult;
	}
	
	/**
	 * 获取组列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/groups")
	@ResponseBody
	public Map<String,Object> groupsData(Model model) {
		Map<String,Object> listResult = new HashMap<String,Object>();
		List<Groups> groupsList = null;
		try {
			/**
			 * news图
			 */
			groupsList = groupsMapper.selectAll();
			listResult.put("data", groupsList);
			listResult.put("rpco", successCode);
			listResult.put("msg", successMsg);
		} catch (Exception e) {
			listResult.put("rpco", failureCode);
			listResult.put("msg", "获取groups列表失败");
			e.printStackTrace();
		}
		
		
		return listResult;
	}
	/**
	 * 按照组查找绘本
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/groupBooks")
	@ResponseBody
	public Map<String,Object> groupBooks(Model model,Long groupId) {
		Map<String,Object> listResult = new HashMap<String,Object>();
		List<PictureBook> pictureList = null;
		try {
			/**
			 * news图
			 */
			if(groupId!=null){
				
				pictureList = pictureMapper.selectBooksByGroupId(groupId);
				listResult.put("data", pictureList);
			}
			listResult.put("rpco", successCode);
			listResult.put("msg", successMsg);
		} catch (Exception e) {
			listResult.put("rpco", failureCode);
			listResult.put("msg", "获取groups列表失败");
			e.printStackTrace();
		}
		
		
		return listResult;
	}
	
	/**
	 * 按照组查找绘本
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/singleBook")
	@ResponseBody
	public Map<String,Object> singleBook(Model model,Long bookId) {
		Map<String,Object> listResult = new HashMap<String,Object>();
		PictureBook pictureBook = null;
		try {
			/**
			 * 单个book
			 */
			if(bookId!=null){
				pictureBook = pictureMapper.selectBookById(bookId);
				listResult.put("data", pictureBook);
			}
			listResult.put("rpco", successCode);
			listResult.put("msg", successMsg);
		} catch (Exception e) {
			listResult.put("rpco", failureCode);
			listResult.put("msg", "获取groups列表失败");
			e.printStackTrace();
		}
		
		
		return listResult;
	}
	
	
	
}
