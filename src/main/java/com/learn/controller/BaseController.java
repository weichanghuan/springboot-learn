package com.learn.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @RequestMapping(value="/getParams",method = RequestMethod.GET)
    @ApiOperation(value="testSwagger2", notes="testSwagger2",httpMethod = "GET")
    public String getParams(@RequestParam(value = "param") String param){
        return "参数为："+param;
    }
}
