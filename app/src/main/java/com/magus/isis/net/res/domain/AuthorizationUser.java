package com.magus.isis.net.res.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Leo on 2016/7/12.
 */
public class AuthorizationUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String userName;

    private Date loginTime;

    // 最后访问时间
    private Date lastAccessTime;

    // 登录令牌
    private String accessToken;

    private Date accessExpiresin;

    // 刷新令牌
    private String refreshToken;

    private Date refreshExpiresin;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getAccessExpiresin() {
        return accessExpiresin;
    }

    public void setAccessExpiresin(Date accessExpiresin) {
        this.accessExpiresin = accessExpiresin;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getRefreshExpiresin() {
        return refreshExpiresin;
    }

    public void setRefreshExpiresin(Date refreshExpiresin) {
        this.refreshExpiresin = refreshExpiresin;
    }

    @Override
    public String toString() {
        return "AuthorizationUser [userId=" + userId + ", userName=" + userName + ", loginTime=" + loginTime + ", lastAccessTime=" + lastAccessTime + ", accessToken=" + accessToken + ", accessExpiresin=" + accessExpiresin
                + ", refreshToken=" + refreshToken + ", refreshExpiresin=" + refreshExpiresin + "]";
    }



}
