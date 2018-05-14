package com.familyRead.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familyRead.dao.FileInfoMapper;
import com.familyRead.model.FileInfo;
import com.familyRead.util.Page;


@Service
public class FileServiceImpl {
	
	@Autowired FileInfoMapper fileInfoMapper;
	
	public int saveFileInfo(FileInfo fileInfo){
		
		return fileInfoMapper.insert(fileInfo);
	}
	
	public List<FileInfo> selectFileInfoPage(Page<FileInfo> page){
		
		return fileInfoMapper.selectFileInfoPage(page);
	}

	public int selectFileInfoCount(Page<FileInfo> page) {
		return fileInfoMapper.selectFileInfoCount(page);
	}

	public int insertSelective(FileInfo fileInfo) {
		int flag = -1;
		FileInfo fi = new FileInfo();
		fi.setFileName(fileInfo.getFileName());
		flag = fileInfoMapper.delete(fi);
		if(flag<0){
			return -1;
		}
		return fileInfoMapper.insertSelective(fileInfo);
		
	}

	public FileInfo selectFileInfoById(FileInfo fileInfo) {
		return fileInfoMapper.selectOne(fileInfo);
	}
}
