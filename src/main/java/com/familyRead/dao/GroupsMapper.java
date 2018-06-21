package com.familyRead.dao;

import java.util.List;

import com.familyRead.model.Groups;
import com.familyRead.util.Page;

public interface GroupsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Groups record);

    int insertSelective(Groups record);

    Groups selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Groups record);

    int updateByPrimaryKey(Groups record);
    
    List<Groups> selectAll();
    
    List<Groups> selectAllGroup();
    
    List<Groups> selectAllCompilation();

	List<Groups> selectGroupPage(Page<Groups> page);
	
	List<Groups> selectByParentId(Long parentId);

	int selectGroupCount(Page<Groups> page);
	
	List<Groups> selectCompilationPage(Page<Groups> page);
	
	int selectCompilationCount(Page<Groups> page);
}