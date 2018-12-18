package com.learn.constant;

import java.io.Serializable;

public final class Constants implements Serializable {

    private static final long serialVersionUID = 1297440719164995115L;

    private Constants() {
    }

    /**
     * 返回码（成功）
     */
    public static final int CODE_SUCCESS = 200;
    /**
     * 返回码（对象有效仍可用）
     */
    public static final int CODE_UNCHANGED = 304;
    /**
     * 返回码（参数错误）
     */
    public static final int CODE_PARAMERROR = 400;
    /**
     * 返回码（不支持的方法）
     */
    public static final int CODE_NOTSUPPORT = 401;
    /**
     * 返回码（未找到该方法）
     */
    public static final int CODE_REJECTED = 403;
    /**
     * 返回码（未找到）
     */
    public static final int CODE_NOTFOUND = 404;
    /**
     * 返回码（系统错误）
     */
    public static final int CODE_SYSTEMERROR = 500;


    /**
     * 返回码（未知错误）
     */
    public static final int UNKNOW_ERROR = 1;
    /**
     * 参数错误
     */
    public static final int PARAM_ERROR = 4;
    /**
     * 业务错误
     */
    public static final int BIZ_ERROR = 8;

    /**
     * 外部错误
     */
    public static final int EXT_ERROR = 9;

    /**
     * 成功
     */
    public static final String MESSAGE_SUCCESS = "成功";

    /**
     * 未知异常
     */
    public static final String MESSAGE_UNKNOW = "未知异常";

    /**
     * 分页数据开始
     */
    public static final String PG_START = "start";

    /**
     * 分页数据数量
     */
    public static final String PG_SIZE = "size";

    /**
     * 默认日期格式（年月日时分秒）
     */
    public static final String DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
    /**
     * 默认日期格式(年月日)
     */
    public static final String DATE_FORMATYMD="yyyy-MM-dd";
    /**
     * 默认日期格式(时分秒)
     */
    public static final String DATE_FORMATHMS="HH:mm:ss";
}
