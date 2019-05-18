package com.example.ychen.myapplication2;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaikun68 on 2018/3/5.
 * <p>
 * ListView演示Demo
 */
public class zrpt extends AppCompatActivity {

    private ListView testLv;//ListView组件
    private Button updateDataBtn;//动态加载数据组件

    private List<String> dataList = new ArrayList<>();//存储数据
    private ArrayAdapter<String> listViewDemoAdapter;//ListView的数据适配器

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zrpt);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        initView();//初始化组件
        initData();//初始化数据
    }

    /**
     * 初始化组件
     */
    private void initView() {
        testLv = (ListView) findViewById(R.id.test_lv);

    }

    /**
     * 初始化数据
     */
    private void initData() {
        getData();
        //设置ListView的适配器
        listViewDemoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        testLv.setAdapter(listViewDemoAdapter);
        testLv.setSelection(4);
    }

    private void getData() {
            String path = "http://10.3.157.234:8080/needinquire";
            String result = "{}";

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
                    result = new String(bos.toByteArray());
                    is.close();

                } else {
                    Toast.makeText(this, "请求失败，失败原因: " + code, Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "请求失败，请检查logcat日志控制台", Toast.LENGTH_LONG).show();
            }

        try {
            JSONArray resultArray = new JSONArray(result);
            for (int i = 0; i < resultArray.length(); ++i) {
                JSONObject json = resultArray.getJSONObject(i);
                String text = "目的地：" + json.getString("destination");
                text += "  送货地：" + json.getString("location");
                text += "  备注：" + json.getString("note");
                dataList.add(text);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

