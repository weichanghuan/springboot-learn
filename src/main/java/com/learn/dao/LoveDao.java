package com.learn.dao;

import com.learn.model.Love;

public interface LoveDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Love record);

    int insertSelective(Love record);

    Love selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Love record);

    int updateByPrimaryKey(Love record);
}