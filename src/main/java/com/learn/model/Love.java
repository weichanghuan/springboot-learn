package com.learn.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * love
 *
 * @author
 */
@Setter
@Getter
@ToString
public class Love implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 描述
     */
    private String desc;

    /**
     * key值
     */
    private String key;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 版本
     */
    private String version;

    private static final long serialVersionUID = 1L;


}