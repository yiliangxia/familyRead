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

import com.familyRead.dao.FileInfoMapper;
import com.familyRead.dao.GroupFileMapper;
import com.familyRead.dao.GroupsMapper;
import com.familyRead.dao.PictureBookMapper;
import com.familyRead.model.Customer;
import com.familyRead.model.Groups;
import com.familyRead.service.impl.FileServiceImpl;
import com.familyRead.util.Page;
import com.google.gson.JsonObject;

/**
 * 
 * @author xyl
 *
 */

@Controller
public class SystemController {
	
	@Autowired FileServiceImpl fileService;
	@Autowired GroupsMapper groupsMapper;
	@Autowired PictureBookMapper pictureBookMapper;
	@Autowired GroupFileMapper groupFileMapper;
	@Autowired FileInfoMapper fileInfoMapper;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	DecimalFormat df = new DecimalFormat("######0.00");
	JsonObject jo = new JsonObject();
	
	@RequestMapping(value = "/groupPage")
	public String groupPage(HttpServletResponse response,Model model) {
		return "system/group";
	}
	
	@RequestMapping(value = "/groupList")
	public String groupList(HttpServletResponse response,Model model,Groups groups,String pageNo,String pageSize) {
		Page<Groups> page = new Page<Groups>();
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
		page.setParameter(groups);
		List<Groups> listResult = groupsMapper.selectGroupPage(page);
		page.setResult(listResult);
		page.setTotalCount(groupsMapper.selectGroupCount(page));
		
		
		logger.debug("-----group管理页-----");
		model.addAttribute("page", page);
		return "system/groupList";
	}
	
	@RequestMapping(value = "/compilationPage")
	public String compilationPage(HttpServletResponse response,Model model) {
		return "system/compilation";
	}
	
	@RequestMapping(value = "/compilationList")
	public String compilationList(HttpServletResponse response,Model model,Groups groups,String pageNo,String pageSize) {
		Page<Groups> page = new Page<Groups>();
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
		page.setParameter(groups);
		List<Groups> listResult = groupsMapper.selectCompilationPage(page);
		if(listResult!=null){
			for(Groups gg:listResult){
				Groups g = groupsMapper.selectByPrimaryKey(gg.getParentId());
				if(g!=null){
					gg.setParentName(g.getGroupName());
				}
			}
		}
		page.setResult(listResult);
		page.setTotalCount(groupsMapper.selectCompilationCount(page));
		
		List<Groups> groupsResult = groupsMapper.selectGroupPage(page);
		logger.debug("-----compilation管理页-----");
		model.addAttribute("page", page);
		model.addAttribute("groups", groupsResult);
		return "system/compilationList";
	}
	
	@RequestMapping(value = "/deleteGroup")
	@ResponseBody
	public String deleteGroup(HttpServletResponse response,Model model,Groups groups) {
		String resultStr = "failure";
		int flag = groupsMapper.deleteByPrimaryKey(groups.getId());
		if(flag!=-1){
			resultStr = "success";
		}
		return resultStr;
	}
	
	@RequestMapping(value = "/saveGroup")
	@ResponseBody
	public String saveGroup(HttpServletRequest request,HttpServletResponse response,Model model,Groups groups) {
		String resultStr = "保存失败";
		int flag = -1;
		Customer ct = (Customer)request.getSession().getAttribute("customer");
		groups.setCareteBy(ct.getUserName());
		if(groups.getId()!=null){
			flag = groupsMapper.updateByPrimaryKeySelective(groups);
		}else{
			flag = groupsMapper.insertSelective(groups);
		}
		if(flag!=-1){
			resultStr = "success";
		}
		return resultStr;
	}
}
