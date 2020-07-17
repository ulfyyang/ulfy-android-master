package com.ulfy.master.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.ulfy.android.system.ActivityUtils;
import com.ulfy.master.ui.base.BaseActivity;

public class RouterActivity extends BaseActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 访问示例：ulfy://master.com/print/?id=431&name=zhouyuan&age=23

        Intent intent = getIntent();
        Uri uri = intent.getData();

        String scheme = intent.getScheme();
        System.out.println("scheme:" + scheme);     // --> action:android.intent.action.VIEW
        String action = intent.getAction();
        System.out.println("action:" + action);     // --> scheme:ulfy

        if (uri != null) {
            String host = uri.getHost();
            System.out.println("host:" + host);                                 // --> host:master.com
            String dataString = intent.getDataString();
            System.out.println("dataString:" + dataString);                     // --> dataString:ulfy://master.com/print/?id=431&name=zhouyuan&age=23
            String queryString = uri.getQuery();
            System.out.println("queryString:" + queryString);                   // --> queryString:id=431&name=zhouyuan&age=23
            String id = uri.getQueryParameter("id");
            System.out.println("id:" + id);                                     // --> id:431
            String name = String.valueOf(uri.getQueryParameters("name"));
            System.out.println("name:" + name);                                 // --> name:[zhouyuan]
            String age = uri.getQueryParameter("age");
            System.out.println("age:" + age);                                   // --> age:23
            String path = uri.getPath();
            System.out.println("path:" + path);                                 // --> path:/print/
            String path1 = uri.getEncodedPath();
            System.out.println("path1:" + path1);                               // --> path1:/print/
        }

        // 还原必须的访问路径
        if (!ActivityUtils.exsitActivity(MainActivity.class)) {
            ActivityUtils.startActivity(MainActivity.class);
        }

        // 根据具体的业务规则启动其它页面
        ActivityUtils.startActivity(List1Activity.class);

        // 关闭自己
        finish();
    }
}
