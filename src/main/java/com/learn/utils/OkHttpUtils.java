package com.learn.utils;


import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName OkHttpUtils
 * @Description
 * @Author Wch
 * @Date 2019-03-26 15:16
 **/
public class OkHttpUtils {

    private Logger logger = LoggerFactory.getLogger(OkHttpUtils.class);

    private final static String JSONTYPE = "application/json; charset=utf-8";

    public final static int READ_TIMEOUT = 100;
    public final static int CONNECT_TIMEOUT = 60;
    public final static int WRITE_TIMEOUT = 60;
    public final static MediaType JSON_MEDIATYPE = MediaType.parse(JSONTYPE);
    private static final byte[] LOCKER = new byte[0];

    private volatile static OkHttpUtils mInstance;

    private OkHttpClient mOkHttpClient;

    private OkHttpUtils() {
        OkHttpClient.Builder ClientBuilder = new OkHttpClient.Builder();
        ClientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);//读取超时
        ClientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);//连接超时
        ClientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);//写入超时
        //支持HTTPS请求，跳过证书验证
        ClientBuilder.sslSocketFactory(createSSLSocketFactory());
        ClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        mOkHttpClient = ClientBuilder.build();
    }

    /**
     * 单例模式获取NetUtils
     *
     * @return
     */
    public static OkHttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (LOCKER) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * doGet请求，同步方式，获取网络数据，是在主线程中执行的，需要新起线程，将其放到子线程中执行
     *
     * @param url
     * @return
     */
    public <T> T doGet(String url, Class<T> clazz) throws Exception {
        return doGet(url, null, clazz);
    }


    /**
     * doGet请求，同步方式，获取网络数据，是在主线程中执行的，需要新起线程，将其放到子线程中执行
     *
     * @param url     请求路径
     * @param headers 请求头
     * @return * @throws Exception
     */
    public <T> T doGet(String url, Map<String, String> headers, Class<T> clazz) throws Exception {
        Response response = null;
        //1 构造Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).headers(addHeaders(headers)).build();
        //2 将Request封装为Call
        Call call = mOkHttpClient.newCall(request);
        //3 执行Call，得到response
        response = call.execute();
        return getResponse(response, clazz);
    }

    /**
     * doPost请求，同步方式，
     *
     * @param url    请求路径
     * @param object 请求对象
     * @return * @throws Exception
     */
    public <T> T doPost(String url, Object object, Class<T> clazz) throws Exception {
        Response response = null;
        return doPostJson(url, JSON.toJSONString(object), clazz);
    }

    /**
     * doPost请求，同步方式，
     *
     * @param url     请求路径
     * @param object  请求对象
     * @param headers 请求头
     * @return
     * @throws Exception
     */
    public <T> T doPost(String url, Object object, Class<T> clazz, Map<String, String> headers) throws Exception {
        Response response = null;
        return doPostJson(url, JSON.toJSONString(object), headers,clazz);
    }

    /**
     * doPost请求，同步方式，
     *
     * @param url        请求路径
     * @param bodyParams 请求KV
     * @param headers    请求头
     * @return
     * @throws Exception
     */
    public <T> T doPost(String url, Map<String, String> bodyParams, Map<String, String> headers, Class<T> clazz) throws Exception {
        //1构造RequestBody
        RequestBody body = setRequestBody(bodyParams);
        //2 构造Request
        Request.Builder requestBuilder = new Request.Builder();
        Request request = requestBuilder.post(body).url(url).headers(addHeaders(headers)).build();
        //3 将Request封装为Call
        Call call = mOkHttpClient.newCall(request);
        //4 执行Call，得到response
        Response response = null;
        response = call.execute();
       return getResponse(response,clazz);
    }

    /**
     * doGet请求，异步方式，获取网络数据，是在子线程中执行的，需要切换到主线程才能更新UI
     *
     * @param url       请求路径
     * @param myNetCall 回调函数
     * @return
     * @throws Exception
     */
    public void doGetAsyn(String url, final MyNetCall myNetCall) throws Exception {
        doGetAsyn(url, null, myNetCall);
    }

    /**
     * doGet请求，异步方式，获取网络数据，是在子线程中执行的，需要切换到主线程才能更新UI
     *
     * @param url       请求路径
     * @param headers   请求头
     * @param myNetCall 回调函数
     * @return
     * @throws Exception
     */
    public void doGetAsyn(String url, Map<String, String> headers, final MyNetCall myNetCall) throws Exception {
        //1 构造Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).headers(addHeaders(headers)).build();
        //2 将Request封装为Call
        Call call = mOkHttpClient.newCall(request);
        //3 执行Call
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                myNetCall.failed(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                myNetCall.success(call, response);

            }
        });
    }

    /**
     * doPost请求，异步方式，
     *
     * @param url       请求路径
     * @param object    请求实体
     * @param myNetCall 回调函数
     * @throws Exception
     */
    public void doPostAsyn(String url, Object object, final MyNetCall myNetCall) throws Exception {
        doPostJsonAysn(url, JSON.toJSONString(object), myNetCall);
    }

    /**
     * doPost请求，异步方式，
     *
     * @param url       请求路径
     * @param object    请求实体
     * @param headers   请求头
     * @param myNetCall 回调函数
     * @throws Exception
     */
    public void doPostAsyn(String url, Object object, Map<String, String> headers, final MyNetCall myNetCall) throws Exception {
        doPostJsonAysn(url, JSON.toJSONString(object), myNetCall, headers);
    }

    /**
     * doPost请求，异步方式，
     *
     * @param url        请求路径
     * @param bodyParams 请求KV
     * @param headers    请求头
     * @param myNetCall  回调函数
     * @throws Exception
     */
    public void doPostAsyn(String url, Map<String, String> bodyParams, Map<String, String> headers, final MyNetCall myNetCall) throws Exception {
        //1构造RequestBody
        RequestBody body = setRequestBody(bodyParams);
        //2 构造Request
        Request.Builder requestBuilder = new Request.Builder();
        Request request = requestBuilder.post(body).url(url).headers(addHeaders(headers)).build();
        //3 将Request封装为Call
        Call call = mOkHttpClient.newCall(request);
        //4 执行Call
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                myNetCall.failed(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                myNetCall.success(call, response);

            }
        });
    }

    /**
     * doPost请求，同步方式，
     *
     * @param url     请求路径
     * @param json    请求json
     * @param headers 请求头
     * @return
     * @throws IOException
     */
    public <T> T doPostJson(String url, String json, Map<String, String> headers, Class<T> clazz) throws Exception {
        RequestBody body = RequestBody.create(JSON_MEDIATYPE, json);
        Request request = new Request.Builder()
                .url(url)
                .headers(addHeaders(headers))
                .post(body)
                .build();
        Response response = mOkHttpClient.newCall(request).execute();
        return getResponse(response, clazz);
    }

    /**
     * doPost请求，同步方式
     *
     * @param url  请求路径
     * @param json 请求json
     * @return
     * @throws IOException
     */
    public <T> T doPostJson(String url, String json, Class<T> clazz) throws Exception {
        return doPostJson(url, json, null, clazz);
    }

    /**
     * doPost请求，异步方式
     *
     * @param url
     * @param json
     * @param myNetCall
     * @param headers
     * @throws IOException
     */
    public void doPostJsonAysn(String url, String json, final MyNetCall myNetCall, Map<String, String> headers) throws IOException {
        RequestBody body = RequestBody.create(JSON_MEDIATYPE, json);
        //2 构造Request
        Request.Builder requestBuilder = new Request.Builder();
        Request request = requestBuilder.post(body).url(url).headers(addHeaders(headers)).build();
        //3 将Request封装为Call
        Call call = mOkHttpClient.newCall(request);
        //4 执行Call
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                myNetCall.failed(call, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                myNetCall.success(call, response);

            }
        });
    }

    /**
     * doPost请求，异步方式
     *
     * @param url
     * @param json
     * @param myNetCall
     * @throws IOException
     */
    public void doPostJsonAysn(String url, String json, final MyNetCall myNetCall) throws IOException {
        doPostJsonAysn(url, json, myNetCall, null);
    }

    /**
     * doPost的请求参数，构造RequestBody
     *
     * @param BodyParams
     * @return
     */
    private RequestBody setRequestBody(Map<String, String> BodyParams) {
        RequestBody body = null;
        FormBody.Builder formEncodingBuilder = new FormBody.Builder();
        if (BodyParams != null) {
            Iterator<String> iterator = BodyParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                formEncodingBuilder.add(key, BodyParams.get(key));
                logger.info("doPost http", "doPost_Params===" + key + "====" + BodyParams.get(key));
            }
        }
        body = formEncodingBuilder.build();
        return body;

    }

    /**
     * 生成安全套接字工厂，用于https请求的证书跳过
     *
     * @return
     */
    public SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }
        return ssfFactory;
    }


    /**
     * 添加header
     *
     * @param header
     * @return
     */
    private Headers addHeaders(Map<String, String> header) {
        if (null == header) {
            header = new HashMap<>();
        }
        return Headers.of(header);

    }

    private <T> T getResponse(Response response, Class<T> clazz) throws Exception {
        if (response.isSuccessful()) {
            String result = response.body().string();
            if (StringUtils.isBlank(result)) {
                logger.info("doPost response is null ");
                return null;
            }
            T t = JSON.parseObject(result, clazz);
        }
        throw new IOException("Unexpected code " + response);
    }

    /**
     * 自定义网络回调接口
     */
    public interface MyNetCall {
        void success(Call call, Response response) throws IOException;

        void failed(Call call, IOException e);
    }

    /**
     * 用于信任所有证书
     */
    class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}



