package com.magus.isis.net;

import com.magus.isis.net.res.AuthorizationUserRes;
import com.magus.isis.net.res.FavoritesRes;
import com.magus.isis.net.res.MenuRes;

import java.io.IOException;

import static com.magus.isis.net.IOkHttpClient.httpGet;
import static com.magus.isis.net.IOkHttpClient.httpPut;

/**
 * Created by Leo on 2016/7/12.
 */
public class NetInterface {

    public static final AuthorizationUserRes putLogin(String u, String p) throws IOException {
        return httpPut(IOkHttpUrlConst.User_Login,u,p,AuthorizationUserRes.class);
    }

    public static final MenuRes getMenu() throws IOException {
        return httpGet(IOkHttpUrlConst.Menu_Get, MenuRes.class);
    }

    public static final FavoritesRes getFavorites(String accessToken) throws IOException {
        return httpGet(IOkHttpUrlConst.Favorites_Get,"token=" + accessToken, FavoritesRes.class);
    }

}
