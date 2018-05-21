package com.familyRead.dao;

import java.util.List;

import com.familyRead.model.PictureBook;
import com.familyRead.util.Page;

public interface PictureBookMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PictureBook record);

    int insertSelective(PictureBook record);

    PictureBook selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PictureBook record);

    int updateByPrimaryKey(PictureBook record);
    
    List<PictureBook> selectBookByPage(Page<PictureBook> page);
    
    int selectBookCount(Page<PictureBook> page);

	List<PictureBook> selectBooksByGroupId(Long groupId);

	PictureBook selectBookById(Long bookId);
}