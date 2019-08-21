package com.example.get_bilibili_follower;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hanks.htextview.base.HTextView;

import org.json.JSONObject;
import org.xutils.x;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    public EditText ed1;
    public TextView tv1,tv2;
    public Button b1;
    public HTextView hTextView1,hTextView2 ;
    String id1,gettofollower,uidname,name,edtext;
    JSONObject data,jsonObject;
    private final static   String urlid = "https://api.bilibili.com/x/relation/stat?vmid="; //uid
    private final static   String url = "https://api.bilibili.com/x/relation/stat?vmid=51896064";//uid
    private final static String upid = null;
    private final static String web_onlineurl = "https://api.bilibili.com/x/web-interface/online";//在线人数接口
    private final static String Getuid_nameurl = "https://api.bilibili.com/x/space/acc/info?mid=";//个人信息接口

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(MainActivity.this);
        setContentView(R.layout.activity_main);
        ed1 =(EditText)findViewById(R.id.Edid);
        tv1 = (TextView)findViewById(R.id.Up_follower1);
        tv2 = (TextView)findViewById(R.id.Up_follower2);
        b1 =(Button)findViewById(R.id.Geturl);
        /**
         * Htextview 样式
         */
        hTextView1 = (HTextView) findViewById(R.id.htext1);
        hTextView2 = (HTextView) findViewById(R.id.htext2);
//        hTextView.animateText("new simple string"); // animate

        GetUp_follower();
        GetUp_follower2();

    }


    /**
     * 获取每一秒刷新一次
     */
    public  void GetUp_follower() {
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
               GetFans();//JSON自动获取UP粉丝数量
               GetFans3();//获取bilibili在线人数
            }
        }, 1000,1000);
    }

    private void GetUp_follower2() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtext = ed1.getText().toString();
                 GetFans4(edtext);//得到edittext 的uid 获取名字
                GetFanss(urlid+edtext);//手动输入uid 获取粉丝数量

            }
        });

    }

    /**
     *JSON自动获取UP粉丝数量
     */
    private void GetFans() {

        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Gson gson = new Gson();

                String responseString2 = response.body().string();

                try {
                    jsonObject = new JSONObject(responseString2);
//                    jsonObject.optString("message");
//                    jsonObject.optInt("code");
//                    jsonObject.optInt("ttl");
                    data = jsonObject.optJSONObject("data");
                    data.optInt("follower");
//                    data.optInt("mid");
//                    data.optInt("following");
//                    data.optInt("whisper");
//                    data.optInt("black");
                    gettofollower = data.getString("follower");
                    hTextView1.animateText("你现在粉丝数量:"+gettofollower+"位");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 自己填写upid获取粉丝数量
     * @param urls
     */
    private  void GetFanss(String urls){
        HttpUtil.sendOkHttpRequest(urls, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Gson gson = new Gson();

                String responseString2 = response.body().string();

                try {
                    jsonObject = new JSONObject(responseString2);
//                    jsonObject.optString("message");
//                    jsonObject.optInt("code");
//                    jsonObject.optInt("ttl");
                    data = jsonObject.optJSONObject("data");
                    data.optInt("follower");
//                    data.optInt("mid");
//                    data.optInt("following");
//                    data.optInt("whisper");
//                    data.optInt("black");
                    gettofollower = data.getString("follower");
                    tv1.setText("粉丝数量为:"+gettofollower+"位");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 获取B站在线人数在线人数
     * @param
     */
    private  void GetFans3(){
        HttpUtil.sendOkHttpRequest(web_onlineurl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Gson gson = new Gson();

                String responseString2 = response.body().string();

                try {
                    jsonObject = new JSONObject(responseString2);
//                    jsonObject.optString("message");
//                    jsonObject.optInt("code");
//                    jsonObject.optInt("ttl");
                    data = jsonObject.optJSONObject("data");
                    //data.optInt("follower");
//                    data.optInt("mid");
//                    data.optInt("following");
//                    data.optInt("whisper");
//                    data.optInt("black");
                    gettofollower = data.getString("web_online");
                    hTextView2.animateText("B站目前在线人数为:"+gettofollower+"位");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }


    /**
     * 获取个人信息
     * @param
     */
    private void GetFans4(String name){
        HttpUtil.sendOkHttpRequest(Getuid_nameurl+name, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Gson gson = new Gson();

                String responseString2 = response.body().string();

                try {
                    jsonObject = new JSONObject(responseString2);
//                    jsonObject.optString("message");
//                    jsonObject.optInt("code");
//                    jsonObject.optInt("ttl");
                    data = jsonObject.optJSONObject("data");
                    //data.optInt("follower");
//                    data.optInt("mid");
//                    data.optInt("following");
//                    data.optInt("whisper");
//                    data.optInt("black");
                    uidname = data.getString("name");
                    tv2.setText("用户名称为 : "+uidname+" ：");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }


}
