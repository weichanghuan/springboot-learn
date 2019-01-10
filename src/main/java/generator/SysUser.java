package generator;

import java.io.Serializable;
import java.util.Date;

/**
 * sys_user
 * @author 
 */
public class SysUser implements Serializable {
    private String userName;

    private String userUid;

    private String password;

    private String fullName;

    private String wxOpenId;

    private String wxUserId;

    /**
     * 手机号码
     */
    private String telephone;

    private String autoLogin;

    private String companyCode;

    private String companyName;

    private String isActive;

    private Date lastGetTime;

    private Date lastLoginTime;

    private String userIcon;

    /**
     * 身份证，唯一的
     */
    private String cardId;

    /**
     * 性别
     */
    private String sex;

    /**
     * 经销商id
     */
    private String dealerId;

    /**
     * 参加工作时间
     */
    private Date joinJobTime;

    /**
     * 入职时间
     */
    private Date entryTime;

    /**
     * 邮箱
     */
    private String mailbox;

    /**
     * 第一岗位
     */
    private String deptOne;

    /**
     * 第二岗位
     */
    private String deptTwo;

    /**
     * 第三岗位
     */
    private String deptThree;

    private static final long serialVersionUID = 1L;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getWxUserId() {
        return wxUserId;
    }

    public void setWxUserId(String wxUserId) {
        this.wxUserId = wxUserId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(String autoLogin) {
        this.autoLogin = autoLogin;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Date getLastGetTime() {
        return lastGetTime;
    }

    public void setLastGetTime(Date lastGetTime) {
        this.lastGetTime = lastGetTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public Date getJoinJobTime() {
        return joinJobTime;
    }

    public void setJoinJobTime(Date joinJobTime) {
        this.joinJobTime = joinJobTime;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getDeptOne() {
        return deptOne;
    }

    public void setDeptOne(String deptOne) {
        this.deptOne = deptOne;
    }

    public String getDeptTwo() {
        return deptTwo;
    }

    public void setDeptTwo(String deptTwo) {
        this.deptTwo = deptTwo;
    }

    public String getDeptThree() {
        return deptThree;
    }

    public void setDeptThree(String deptThree) {
        this.deptThree = deptThree;
    }
}