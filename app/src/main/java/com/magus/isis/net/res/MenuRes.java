package com.magus.isis.net.res;

import com.magus.isis.net.res.base.BaseRes;
import com.magus.isis.net.res.domain.AuthorizationUser;
import com.magus.isis.net.res.domain.MenuDomain;

import java.util.List;

/**
 * Created by Leo on 2016/7/12.
 */
public class MenuRes extends BaseRes {

    private List<MenuDomain> data;

    public MenuRes() {
    }

    public List<MenuDomain> getData() {
        return data;
    }

    public void setData(List<MenuDomain> data) {
        this.data = data;
    }
}
