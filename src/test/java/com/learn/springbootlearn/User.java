package com.learn.springbootlearn;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Description
 * @Author Wch
 * @Date 2019-03-26 16:18
 **/
public class User implements Serializable {

    private Integer id;

    private String name;

    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
