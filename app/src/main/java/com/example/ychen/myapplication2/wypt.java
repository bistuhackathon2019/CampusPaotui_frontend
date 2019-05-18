package com.example.ychen.myapplication2;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class wypt extends AppCompatActivity {
    private EditText wherething;
    private EditText whereyou;
    private EditText tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wypt);


        Button button = (Button) findViewById(R.id.btnOne);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("dd","dsf");
                LoginByGet(v);
//
            }
        });

        EditText tip1 = (EditText) findViewById(R.id.et_user_name);

         wherething = (EditText) findViewById(R.id.wherething);
       whereyou = (EditText) findViewById(R.id.whereyou);
        tip = (EditText) findViewById(R.id.tip);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    public void LoginByGet (View view) {

        String whereth= wherething.getText().toString().trim();
        String whereyo= whereyou.getText().toString().trim();
        String ti= tip.getText().toString().trim();


        /*if (TextUtils.isEmpty(whereth) || TextUtils.isEmpty(whereyo)) {
            Toast.makeText(this, "用户名密码不能为空", Toast.LENGTH_LONG).show();
        } else*/ {
            // 模拟http请求，提交数据到服务器
            String path = "http://10.3.157.234:8080/need?" + "destination="+whereth+"&location="+whereyo+"&note="+ti;

            try {
                URL url = new URL(path);
                // 2.建立一个http连接
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                // 3.设置一些请求方式
                conn.setRequestMethod("GET");// 注意GET单词字幕一定要大写
                conn.setRequestProperty(
                        "User-Agent",
                        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");

                int code = conn.getResponseCode(); // 服务器的响应码 200 OK //404 页面找不到
                // // 503服务器内部错误
                if (code == 200) {
                    InputStream is = conn.getInputStream();
                    // 把is的内容转换为字符串
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    while ((len = is.read(buffer)) != -1) {
                        bos.write(buffer, 0, len);
                    }
                    String result = new String(bos.toByteArray());
                    is.close();
                    Toast.makeText(this, result, Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this, "请求失败，失败原因: " + code, Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "请求失败，请检查logcat日志控制台", Toast.LENGTH_LONG).show();
            }

        }
    }
}

