package com.learn.springbootlearn;

import com.alibaba.fastjson.JSON;

import java.util.Date;
import java.util.Map;

/**
 * @ClassName TestJson
 * @Description
 * @Author Wch
 * @Date 2019-03-26 16:11
 **/
public class TestJson {

    public static void main(String[] args) {
        User user=new User();
        user.setDate(new Date());
        user.setId(1);
        user.setName("ok小子");
        Map<String,String> map= (Map<String,String>) JSON.toJSON(user);
        System.out.println("end");

    }
}
