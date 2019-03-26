package com.learn.utils;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import reactor.core.publisher.Mono;

import java.util.Map;


/**
 * @ClassName WebClientUtil
 * @Description 基于webClient的简易封装
 * @Author Wch
 * @Date 2019-03-25 14:41
 **/
public class WebClientUtil {

    //Json
    private static final MediaType MEDIATYPE = MediaType.APPLICATION_JSON_UTF8;


    /**
     * 基于请求路径 和请求类型 封装
      * @param url   请求路径
     * @param method 请求类型
     * @return
     */
    private static RequestBodySpec uri(String url, HttpMethod method) {
        return WebClient.create().method(method).uri(url);
    }

    /**
     * @param paramter   请求参数
     * @param url        请求路径
     * @param resultType 返回结果类型
     * @return
     */
    public static <V> V blockPost(Object paramter, String url, Class<V> resultType) throws Exception {

        return blockPost(uri(url, HttpMethod.POST), paramter, resultType);
    }

    /**
     * @param paramter   请求参数
     * @param url        请求路径
     * @param header     请求头
     * @param cookies    cookies
     * @param resultType 返回结果类型
     * @return
     */
    public static <V> V blockPost(Object paramter, String url, Map<String, String> header, Map<String, String> cookies,Class<V> resultType) throws Exception{

        WebClient.RequestBodySpec uri = uri(url, HttpMethod.POST);
        addHeader(header, uri);
        addCookies(cookies,uri);
        return blockPost(uri, paramter, resultType);
    }



    /**
     * 阻塞式调用
     * @param uri  RequestBodySpec
     * @param paramter  请求参数
     * @param resultType  返回结果类型
     * @return
     */
    private static <V> V blockPost(RequestBodySpec uri, Object paramter, Class<V> resultType) throws Exception{

        return uri.contentType(MEDIATYPE).body(Mono.just(paramter), Object.class).retrieve().bodyToMono(resultType)
                .block();
    }

    /**
     * 非阻塞式调用
     * @param uri  RequestBodySpec
     * @param paramter  请求参数
     * @param resultType  返回结果类型
     * @return
     */
    private static <V> Mono<V> nonBlockPost(RequestBodySpec uri, Object paramter, Class<V> resultType) throws Exception{

        return uri.contentType(MEDIATYPE).body(Mono.just(paramter), Object.class).retrieve().bodyToMono(resultType);

    }

    /**
     * 阻塞式调用
     * @param url        请求路径
     * @param resultType 返回结果类型
     * @return
     */
    public static <V> V get(String url, Class<V> resultType) throws Exception{

        return uri(url, HttpMethod.GET).retrieve().bodyToMono(resultType).block();
    }

    /**
     * @param url        请求路径
     * @param header     请求头
     * @param resultType 返回结果类型
     * @return
     */
    public static <V> V get(String url, Map<String, String> header, Class<V> resultType) throws Exception{

        RequestBodySpec uri = uri(url, HttpMethod.GET);
        addHeader(header, uri);
        return uri.retrieve().bodyToMono(resultType).block();
    }


    /**
     * 设置cookie
     * @param header
     * @param uri
     */
    private static void addCookies(Map<String, String> header, RequestBodySpec uri) {
        if (!CollectionUtils.isEmpty(header)) {
            header.forEach((name, value) -> uri.cookie(name, value));
        }
    }

    /**
     * 设置请求头
     * @param header
     * @param uri
     */
    private static void addHeader(Map<String, String> header, RequestBodySpec uri) {
        if (!CollectionUtils.isEmpty(header)) {
            header.forEach((name, value) -> uri.header(name,value));
        }
    }

    private WebClientUtil() {

    }


}
