package com.learn.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * test_table
 * @author 
 */
@Setter
@Getter
@ToString
public class TestTable implements Serializable {
    /**
     * 主键
     */
    private Long id;

    private String name;

    private String url;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;


}