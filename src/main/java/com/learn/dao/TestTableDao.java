package com.learn.dao;

import com.learn.model.TestTable;

public interface TestTableDao {
    int deleteByPrimaryKey(Long id);

    int insert(TestTable record);

    int insertSelective(TestTable record);

    TestTable selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestTable record);

    int updateByPrimaryKey(TestTable record);
}