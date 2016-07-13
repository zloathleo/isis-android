package com.magus.isis.net.res.base;

import java.io.Serializable;

/**
 * Created by Leo on 2016/7/12.
 */
public class BaseRes implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String msg;

    public BaseRes() {
    }

    public BaseRes(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
