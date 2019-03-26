package com.learn.securityconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @ClassName WebSecurityConfig
 * @Description
 * @Author Wch
 * @Date 2019-03-26 11:07
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private static  String[] allowedUrl={"/","/ok"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers(allowedUrl).permitAll()
//                .antMatchers(HttpMethod.OPTIONS).permitAll()
//                //所有请求需要身份认证
//                .anyRequest().authenticated()
//                .and().cors()
//                .and()
//                // 添加一个过滤器 所有访问 /login 的请求交给 ApiLoginFilter 来处理 这个类处理所有的JWT相关内容
//                .addFilterBefore(new ApiLoginFilter("/api/v1/login", authenticationManager(), tokenAuthenticationService),
//                        UsernamePasswordAuthenticationFilter.class)
    }
}
