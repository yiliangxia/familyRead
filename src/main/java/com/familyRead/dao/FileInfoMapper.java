package com.familyRead.dao;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.familyRead.model.FileInfo;
import com.familyRead.util.Page;


public interface FileInfoMapper extends Mapper<FileInfo>{

	List<FileInfo> selectFileInfoPage(Page<FileInfo> page);

	int selectFileInfoCount(Page<FileInfo> page);
	@Override
	public int insertSelective(FileInfo fileInfo);
}