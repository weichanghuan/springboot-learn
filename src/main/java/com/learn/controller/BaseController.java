package com.learn.controller;


import com.learn.dao.TestTableDao;
import com.learn.utils.JSONUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/diy")
@Slf4j
public class BaseController {

    @Autowired
    TestTableDao testTableDao;

    @RequestMapping(value = "/getParams", method = RequestMethod.GET)
    @ApiOperation(value = "testSwagger2", notes = "testSwagger2", httpMethod = "GET")
    public String getParams(@RequestParam(value = "param") String param) {
        log.info("参数为：{},",param);
        return "参数为：" + param;
    }

    @RequestMapping(value = "/testDb", method = RequestMethod.GET)
    @ApiOperation(value = "testDb", notes = "testDb", httpMethod = "GET")
    public String testDb(@RequestParam(value = "id") Long id) {
        return JSONUtil.toJSonString(testTableDao.selectByPrimaryKey(id));
    }
}
