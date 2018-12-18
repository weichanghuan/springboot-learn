package com.learn.model.base;

import java.io.Serializable;

import com.learn.constant.Constants;
import org.joda.time.DateTime;

/**
 * api信息
 */
public class ApiInfo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5230486022729614685L;

    /**
     * 服务器响应时间
     */
    private String operationTime;
    /**
     * 接口版本或者项目版本
     */
    private String version;
    /**
     * 服务器hostName或者ip
     */
    private String hostName;

    public String getOperationTime() {
        operationTime = DateTime.now().toString(Constants.DATE_FORMAT);
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @Override
    public String toString() {
        return "ApiInfo [operationTime=" + operationTime + ", version=" + version + ", hostName=" + hostName + "]";
    }

}
