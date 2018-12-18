package com.learn.model.base;

import java.io.Serializable;

/**
 * 服务接口返回对象。
 * <p>
 * 客户端调用服务方法，得到<code>ServiceResult</code>对象后：<br />
 * 1. 判断{@link ServiceResult#isSuccess()}，为true表示服务方法执行成功，为false表示服务方法执行失败；<br />
 * 2. 服务执行成功时，通过{@link ServiceResult#getResult()}取执行结果； 使用分页查询列表的服务接口，应当通过
 * {@link ServiceResult#getPage()}返回分页信息（总记录数等）；<br />
 * 3. 服务执行失败时，通过{@link ServiceResult#getMessage()}取错误描述消息，详细错误信息从服务日志中查询；
 * </p>
 */
public class ServiceResult<T> implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3377908754816970210L;
    /**
     * 返回值
     */
    private T result;
    /**
     * 分页信息
     */
    private Page page;
    /**
     * 调用是否成功
     */
    private boolean isSuccess = true;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回码
     */
    private int code;

    /**
     * 服务执行失败时，获取错误描述消息。
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置消息
     *
     * @param message
     *        message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 服务是否执行成功。
     *
     * @return isSuccess
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * 设置是否成功
     *
     * @param success
     *        是否成功
     */
    public void setSuccess(boolean success) {
        this.isSuccess = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 获取服务执行结果。
     *
     * @return result
     */
    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    /**
     * 获取分页信息。
     * <p>
     * 只有某些使用分页查询列表的方法才会返回分页信息，除此之外均返回null。具体参考各服务方法说明。
     *
     * @return Page
     */
    public Page getPage() {
        return this.page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "ServiceResult [result=" + result + ", page=" + page + ", isSuccess=" + isSuccess + ", message="
                + message + ", code=" + code + "]";
    }
}

