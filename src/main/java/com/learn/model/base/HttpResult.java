package com.learn.model.base;


import com.learn.constant.Constants;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

/**
 * @author 危常焕
 */
public class HttpResult<T> implements java.io.Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2366743359299689374L;
    /**
     * 返回提示信息
     */
    private String message;
    /**
     * 返回业务状态(各个模块可以自定义)
     */
    private int code;
    /**
     * 返回数据(T)
     */
    private T data;

    /**
     * 请求头
     */
    private String header;
    /**
     * 分页信息
     */
    private Page page;
    /**
     * api信息
     */
    private ApiInfo apiInfo;

    public HttpResult() {
        this.code = Constants.CODE_SUCCESS;
        this.message = Constants.MESSAGE_SUCCESS;
    }

    /**
     * @param code
     *        业务状态编码
     * @param message
     *        提示信息
     * @param data
     *        返回数据
     */
    public HttpResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        getApiInfo().setOperationTime(DateTime.now().toString(Constants.DATE_FORMAT));
    }

    /**
     * @param code
     *        业务状态编码
     * @param message
     *        提示信息
     */
    public HttpResult(int code, String message) {
        this.code = code;
        this.message = message;
        getApiInfo().setOperationTime(DateTime.now().toString(Constants.DATE_FORMAT));
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;

    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public ApiInfo getApiInfo() {
        if (null == apiInfo)
            apiInfo = new ApiInfo();
        return apiInfo;
    }

    public void setApiInfo(ApiInfo foot) {
        this.apiInfo = foot;
    }

    @Override
    public String toString() {
        return "HttpResult [message=" + message + ", code=" + code + ", data=" + data
                + ", header=" + header + ", page=" + page + ", apiInfo=" + apiInfo + "]";
    }

    /**
     * 根据服务端的返回结果转化为HttpResult
     * @param serviceResult 服务端返回值
     * @return HttpResult http返回值
     */
    public final HttpResult<T> fromServicResult(ServiceResult<T> serviceResult){

        if(serviceResult == null){
            this.setCode(Constants.CODE_SYSTEMERROR);
            this.setMessage(Constants.MESSAGE_UNKNOW);
            return this;
        }

        this.setData(serviceResult.getResult());
        this.setCode(serviceResult.isSuccess()?Constants.CODE_SUCCESS:serviceResult.getCode());
        this.setMessage(StringUtils.isNotBlank(serviceResult.getMessage())?serviceResult.getMessage():Constants.MESSAGE_SUCCESS);
        this.setPage(serviceResult.getPage());
        return this;
    }
}
