package com.familyRead.dao;

import com.familyRead.model.PictureBook;

public interface PictureBookMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PictureBook record);

    int insertSelective(PictureBook record);

    PictureBook selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PictureBook record);

    int updateByPrimaryKey(PictureBook record);
}