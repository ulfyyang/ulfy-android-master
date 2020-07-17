package com.ulfy.master.infrastructure.http;

import com.ulfy.master.domain.entity.Movie;

import java.util.HashMap;
import java.util.Map;

/**
 * 对接后台的业务接口，该类的结构和方法的编写顺序要按照后台接口文档的顺序编写
 */
public class AppHttpUtils {

    ///////////////////////////////////////////////////////////////////////////
    // 分块分割：这里编写按照模块分割的名字，例如：登录注册
    ///////////////////////////////////////////////////////////////////////////

//    public static User login(String userName, String password) throws Exception {
//        String urlPart = "/login";
//        Map<Object, Object> params = new HashMap<>();
//        params.put("userName", userName);
//        params.put("password", password);
//        return FormJsonHttpUtils.requestObject(urlPart, params, User.class);
//    }

    ///////////////////////////////////////////////////////////////////////////
    // 信息获取
    ///////////////////////////////////////////////////////////////////////////

    public static Movie getMovieInfo(int id) throws Exception {
        String urlPart = "/getMovieInfo";
        Map<Object, Object> params = new HashMap<>();
        params.put("id", id);
        return FormJsonHttpUtils.requestObject(urlPart, params, Movie.class);
    }
}
