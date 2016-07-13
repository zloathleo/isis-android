package com.magus.isis.net.res;

import com.magus.isis.net.res.base.BaseRes;
import com.magus.isis.net.res.domain.FavoritesDomain;
import com.magus.isis.net.res.domain.MenuDomain;

import java.util.List;

/**
 * Created by Leo on 2016/7/12.
 */
public class FavoritesRes extends BaseRes {

    private List<FavoritesDomain> data;

    public FavoritesRes() {
    }

    public List<FavoritesDomain> getData() {
        return data;
    }

    public void setData(List<FavoritesDomain> data) {
        this.data = data;
    }
}
