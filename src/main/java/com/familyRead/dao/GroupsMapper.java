package com.familyRead.dao;

import java.util.List;

import com.familyRead.model.Groups;

public interface GroupsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Groups record);

    int insertSelective(Groups record);

    Groups selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Groups record);

    int updateByPrimaryKey(Groups record);
    
    List<Groups> selectAll();
}