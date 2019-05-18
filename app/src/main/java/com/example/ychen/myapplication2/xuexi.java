package com.example.ychen.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class xuexi extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xuexi);
        Button button = (Button) findViewById(R.id.btnOne);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
// 用类名跳转，需要在AndroidManifest.xml中申明activ
                Intent intent = new Intent(xuexi.this, zrjd.class);
                startActivity(intent);
            }
        });


        Button button2 = (Button) findViewById(R.id.btnTwo);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
// 用类名跳转，需要在AndroidManifest.xml中申明activ
                Intent intent2 = new Intent(xuexi.this, zrjd.class);
                startActivity(intent2);
            }
        });

    }



}
