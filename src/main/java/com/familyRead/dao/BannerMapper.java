package com.familyRead.dao;

import java.util.List;

import com.familyRead.model.Banner;
import com.familyRead.util.Page;

public interface BannerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);

	List<Banner> selectBannerPage(Page<Banner> page);

	int selectBannerCount(Page<Banner> page);

	List<Banner> selectNewsByBannerType(Integer bannerType);

	List<Banner> selectBannerByBannerType(Integer bannerType);
}