package com.familyRead.controller.system;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familyRead.dao.BannerMapper;
import com.familyRead.dao.FileInfoMapper;
import com.familyRead.dao.GroupsMapper;
import com.familyRead.model.Banner;
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
public class BannerController {
	
	@Autowired FileServiceImpl fileService;
	@Autowired GroupsMapper groupsMapper;
	@Autowired FileInfoMapper fileInfoMapper;
	@Autowired BannerMapper bannerMapper;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	DecimalFormat df = new DecimalFormat("######0.00");
	JsonObject jo = new JsonObject();
	
	@RequestMapping(value = "/bannerPage")
	public String groupPage(HttpServletResponse response,Model model) {
		return "system/banner";
	}
	@RequestMapping(value = "/bannerList")
	public String groupList(HttpServletResponse response,Model model,Banner banner,String pageNo,String pageSize) {
		Page<Banner> page = new Page<Banner>();
		Integer _pageSize = 10;
		if(!StringUtils.isBlank(pageSize)){
			_pageSize=Integer.parseInt(pageSize);
		}
		page.setPageSize(_pageSize);
		int _pageNo = 1;
		
		if (pageNo != null && !"".equals(pageNo)) {
			try {
				_pageNo = Integer.parseInt(pageNo);
				page.setPageNo(_pageNo);
			} catch (Exception e) {
				logger.info("页码转换失败{}", pageNo);
			}
		}
		page.setParameter(banner);
		List<Banner> listResult = bannerMapper.selectBannerPage(page);
		page.setResult(listResult);
		page.setTotalCount(bannerMapper.selectBannerCount(page));
		
		
		logger.debug("-----group管理页-----");
		
		FileInfo param = new FileInfo(1);
		List<FileInfo> imgList = fileInfoMapper.selectFileInfoByParams(param);
		
		model.addAttribute("page", page);
		model.addAttribute("imgList", imgList);
		return "system/bannerList";
	}
	@RequestMapping(value = "/deleteBanner")
	@ResponseBody
	public String deleteGroup(HttpServletResponse response,Model model,Banner banner) {
		String resultStr = "failure";
		int flag = bannerMapper.deleteByPrimaryKey(banner.getId());
		if(flag!=-1){
			resultStr = "success";
		}
		return resultStr;
	}
	
	@RequestMapping(value = "/saveOrUpdateBanner")
	@ResponseBody
	public String saveGroup(HttpServletRequest request,HttpServletResponse response,Model model,Banner banner) {
		String resultStr = "保存失败";
		int flag = -1;
//		Customer ct = (Customer)request.getSession().getAttribute("customer");
		if(banner.getId()!=null){
			flag = bannerMapper.updateByPrimaryKeySelective(banner);
		}else{
			flag = bannerMapper.insertSelective(banner);
		}
		if(flag!=-1){
			resultStr = "success";
		}
		return resultStr;
	}
}
