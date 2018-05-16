package com.familyRead.dao;

import com.familyRead.model.GroupFile;

public interface GroupFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GroupFile record);

    int insertSelective(GroupFile record);

    GroupFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupFile record);

    int updateByPrimaryKey(GroupFile record);
}