package com.learn.exception;

/**
 * 参数错误，方法调用的入参不符合要求
 */
public class ArgumentException extends BusinessException {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -6018660026145924294L;

    /**
     * @param message
     *        异常信息
     */
    public ArgumentException(String message) {
        super(message);
    }
}
