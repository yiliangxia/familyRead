package com.familyRead.dao;

import java.util.List;

import com.familyRead.model.FileInfo;
import com.familyRead.util.Page;

public interface FileInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);
	
	List<FileInfo> selectFileInfoPage(Page<FileInfo> page);
	List<FileInfo> selectPdfFileInfoPage(Page<FileInfo> page);
	List<FileInfo> selectVedioFileInfoPage(Page<FileInfo> page);

	int selectFileInfoCount(Page<FileInfo> page);
	
	List<FileInfo> selectFileInfoByParams(FileInfo fileInfo);

	int deleteByFileName(FileInfo fileInfo);
}