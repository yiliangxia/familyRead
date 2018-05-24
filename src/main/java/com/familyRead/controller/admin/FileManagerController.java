package com.familyRead.controller.admin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.familyRead.dao.FileInfoMapper;
import com.familyRead.dao.GroupFileMapper;
import com.familyRead.dao.GroupsMapper;
import com.familyRead.dao.PictureBookMapper;
import com.familyRead.model.Customer;
import com.familyRead.model.FileInfo;
import com.familyRead.model.Groups;
import com.familyRead.model.PictureBook;
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
	@Autowired GroupsMapper groupsMapper;
	@Autowired PictureBookMapper pictureBookMapper;
	@Autowired GroupFileMapper groupFileMapper;
	@Autowired FileInfoMapper fileInfoMapper;
	
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
        // 1、解析文件数据，并存入数据库  
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
	
	@RequestMapping(value = "/toImgManage")
	public String toImgManage(HttpServletResponse response,Model model) {
		return "admin/imgManage";
	}
	@RequestMapping(value = "/imgManageList")
	public String imgManageList(HttpServletResponse response,Model model,FileInfo fileInfo,String pageNo,String pageSize) {
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
		List<FileInfo> listResult = fileInfoMapper.selectFileInfoPage(page);
		if(!listResult.isEmpty()){
			for(FileInfo fi : listResult){
				String groupName = "";
				String groups = fi.getGroupId();
				if(groups!=null&&groups.contains(",")){
					for(String str:groups.split(",")){
						Groups gs = groupsMapper.selectByPrimaryKey(Long.valueOf(str));
						groupName+=gs.getGroupName()+",";
					}
				}
				if(groups!=null&&!groups.contains(",")){
					Groups gs = groupsMapper.selectByPrimaryKey(Long.valueOf(groups));
					groupName = gs.getGroupName();
				}
				if(groupName.contains(",")){
					groupName = groupName.substring(0, groupName.length()-1);
				}
				fi.setGroupName(groupName);
			}
		}
		page.setResult(listResult);
		page.setTotalCount(fileService.selectFileInfoCount(page));
		
		
		logger.debug("-----img管理页-----");
		model.addAttribute("page", page);
		return "admin/imgList";
	}
	
	@RequestMapping(value = "/toDocManage")
	public String toDocManage(HttpServletResponse response,Model model) {
		return "admin/docManage";
	}
	@RequestMapping(value = "/toDocList")
	public String toDocList(HttpServletResponse response,Model model,FileInfo fileInfo,String pageNo,String pageSize) {
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
		List<FileInfo> listResult = fileInfoMapper.selectPdfFileInfoPage(page);
		if(!listResult.isEmpty()){
			for(FileInfo fi : listResult){
				String groupName = "";
				String groups = fi.getGroupId();
				if(groups!=null&&groups.contains(",")){
					for(String str:groups.split(",")){
						Groups gs = groupsMapper.selectByPrimaryKey(Long.valueOf(str));
						groupName+=gs.getGroupName()+",";
					}
				}
				if(groups!=null&&!groups.contains(",")){
					Groups gs = groupsMapper.selectByPrimaryKey(Long.valueOf(groups));
					groupName = gs.getGroupName();
				}
				if(groupName.contains(",")){
					groupName = groupName.substring(0, groupName.length()-1);
				}
				fi.setGroupName(groupName);
			}
		}
		page.setResult(listResult);
		page.setTotalCount(fileService.selectFileInfoCount(page));
		
		
		logger.debug("-----文档管理页-----");
		model.addAttribute("page", page);
		return "admin/docList";
	}
	
	@RequestMapping(value = "/toVedioManage")
	public String toVedioManage(HttpServletResponse response,Model model) {
		return "admin/vedioManage";
	}
	@RequestMapping(value = "/vedioList")
	public String vedioList(HttpServletResponse response,Model model,FileInfo fileInfo,String pageNo,String pageSize) {
		Page<FileInfo> page = new Page<FileInfo>();
		Integer _pageSize = 10;
		if(!StringUtils.isBlank(pageSize)){
			_pageSize=Integer.parseInt(pageSize);
		}
		page.setPageSize(_pageSize);
		int _pageNo = 1;
		fileInfo.setFileType(3);
		if (pageNo != null && !"".equals(pageNo)) {
			try {
				_pageNo = Integer.parseInt(pageNo);
				page.setPageNo(_pageNo);
			} catch (Exception e) {
				logger.info("页码转换失败{}", pageNo);
			}
		}
		
		page.setParameter(fileInfo);
		List<FileInfo> listResult = fileInfoMapper.selectVedioFileInfoPage(page);
		if(!listResult.isEmpty()){
			for(FileInfo fi : listResult){
				String groupName = "";
				String groups = fi.getGroupId();
				if(groups!=null&&groups.contains(",")){
					for(String str:groups.split(",")){
						Groups gs = groupsMapper.selectByPrimaryKey(Long.valueOf(str));
						groupName+=gs.getGroupName()+",";
					}
				}
				if(groups!=null&&!groups.contains(",")){
					Groups gs = groupsMapper.selectByPrimaryKey(Long.valueOf(groups));
					groupName = gs.getGroupName();
				}
				if(groupName.contains(",")){
					groupName = groupName.substring(0, groupName.length()-1);
				}
				fi.setGroupName(groupName);
			}
		}
		page.setResult(listResult);
		page.setTotalCount(fileService.selectFileInfoCount(page));
		
		
		logger.debug("-----音频管理页-----");
		model.addAttribute("page", page);
		return "admin/vedioList";
	}
	
	
	@RequestMapping(value = "/toBookManage")
	public String toBookManage(Model model,FileInfo fileInfo) {
		if(fileInfo!=null){
			fileInfo = fileService.selectFileInfoByFileId(fileInfo.getId());
		}
		List<Groups> groupList = groupsMapper.selectAll();
		FileInfo param = new FileInfo(1);
		List<FileInfo> imgList = fileInfoMapper.selectFileInfoByParams(param);
		param.setFileType(2);
		List<FileInfo> docList = fileInfoMapper.selectFileInfoByParams(param);
		param.setFileType(3);
		List<FileInfo> vedioList = fileInfoMapper.selectFileInfoByParams(param);
		
		
		model.addAttribute("fileInfo", fileInfo);
		model.addAttribute("groupList", groupList);
		model.addAttribute("imgList", imgList);
		model.addAttribute("docList", docList);
		model.addAttribute("vedioList", vedioList);
		return "admin/bookManage";
	}
	
	@RequestMapping(value = "/bookPage")
	public String bookPage(Model model,PictureBook pictureBook) {
		
		return "admin/bookPage";
	}
	
	@RequestMapping(value = "/bookList")
	public String bookList(Model model,PictureBook pictureBook,String pageNo,String pageSize) {
		Page<PictureBook> page = new Page<PictureBook>();
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
		page.setParameter(pictureBook);
		List<PictureBook> listResult = pictureBookMapper.selectBookByPage(page);
		if(!listResult.isEmpty()){
			for(PictureBook pb:listResult){
				String groupsId = pb.getGroupId();
				String groupName = "";
				
				if(groupsId!=null&&groupsId.contains(",")){
					for(String str:groupsId.split(",")){
						Groups gs = groupsMapper.selectByPrimaryKey(Long.valueOf(str));
						groupName+=gs.getGroupName()+",";
					}
				}
				if(groupsId!=null&&!groupsId.contains(",")){
					Groups gs = groupsMapper.selectByPrimaryKey(Long.valueOf(groupsId));
					groupName = gs.getGroupName();
				}
				if(groupName.contains(",")){
					groupName = groupName.substring(0, groupName.length()-1);
				}
				pb.setGroupName(groupName);
			}
		}
		page.setResult(listResult);
		page.setTotalCount(pictureBookMapper.selectBookCount(page));
		
		logger.debug("-----音频管理页-----");
		model.addAttribute("page", page);
		return "admin/bookList";
	}
	
	
	@RequestMapping(value = "/saveBook" ,method=RequestMethod.POST)
	public String saveBook(HttpServletRequest request,Model model,PictureBook pictureBook) {
			String groups = "";
			for(String key:request.getParameterMap().keySet()){
				if(key.startsWith("group")){
					groups+=String.valueOf(request.getParameter(key))+",";
				}
			}
			if(groups.contains(",")){
				groups = groups.substring(0, groups.lastIndexOf(","));
			}
			pictureBook.setGroupId(groups);
			int flag = pictureBookMapper.insertSelective(pictureBook);
			if(flag!=-1){
				model.addAttribute("status",pictureBook.getBookName()+"创建成功");
			}
		return "redirect:/toBookManage";
	}
	
	
	@RequestMapping(value = "/deleteFile")
	@ResponseBody
	public String saveGroup(HttpServletRequest request,HttpServletResponse response,Model model,FileInfo fileInfo) {
		String resultStr = "删除失败";
		int flag = -1;
			flag = fileInfoMapper.deleteByPrimaryKey(fileInfo.getId());
		if(flag!=-1){
			String uploadPath = request.getSession().getServletContext().getRealPath("/")+"assets/upload";
			String filePath = uploadPath+File.separator+fileInfo.getFileName();
	        File ff = new File(filePath);
	        if(ff.exists()){
	        	ff.delete();
	        }
			resultStr = "success";
		}
		return resultStr;
	}

}
