package com.magus.isis.net.res;

import com.magus.isis.net.res.base.BaseRes;
import com.magus.isis.net.res.domain.AuthorizationUser;

/**
 * Created by Leo on 2016/7/12.
 */
public class AuthorizationUserRes extends BaseRes {

    private AuthorizationUser data;

    public AuthorizationUserRes() {
    }

    public AuthorizationUser getData() {
        return data;
    }

    public void setData(AuthorizationUser data) {
        this.data = data;
    }
}
