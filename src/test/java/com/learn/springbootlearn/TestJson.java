package com.learn.springbootlearn;

import com.learn.utils.JSONUtil;
import com.learn.utils.OkHttpUtils;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestJson
 * @Description
 * @Author Wch
 * @Date 2019-03-26 16:11
 **/
public class TestJson {

    public static void main(String[] args) {
        String url = "http://localhost:10000/api/v1/lot/test";
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("tenantId", "1");
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        Response post = null;
        try {
            post = OkHttpUtils.getInstance().doGet(url+"?id=1");
            if(post.isSuccessful()){
                ResponseBody body = post.body();
                System.out.println(body.string());
            }
            
            System.out.println("end1");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("end");

    }
}
