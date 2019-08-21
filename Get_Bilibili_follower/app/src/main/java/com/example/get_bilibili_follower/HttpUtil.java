package com.example.get_bilibili_follower;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {

    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        //下面是OkHttp的使用步骤
        //首先创建OkHttpClient的对象
        OkHttpClient client = new OkHttpClient();
        //通过创建Request对象将URL绑定
        Request request = new Request.Builder().url(address).build();
        //添加回调方法，这样OkHttp的使用就完成了，在需要访问网络时只需要调用这个方法就可以了
        client.newCall(request).enqueue(callback);

    }

}
