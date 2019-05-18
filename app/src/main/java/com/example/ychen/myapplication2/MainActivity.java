package com.example.ychen.myapplication2;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
  import android.view.View;
 import android.widget.Button;
 import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




         Button button = (Button) findViewById(R.id.btnOne);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
// 用类名跳转，需要在AndroidManifest.xml中申明activ
                Intent intent = new Intent(MainActivity.this, paotui.class);
                startActivity(intent);
            }
        });


        Button button2 = (Button) findViewById(R.id.btnTwo);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
// 用类名跳转，需要在AndroidManifest.xml中申明activ
                Intent intent2 = new Intent(MainActivity.this,xuexi.class);
                startActivity(intent2);
            }
        });


        Button button3 = (Button) findViewById(R.id.btnThree);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
// 用类名跳转，需要在AndroidManifest.xml中申明activ
                Intent intent3= new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent3);
            }
        });




    }
}



/*
   public void (View v) {
        switch (v.getId()) {
            case R.id.btnOne:
                Toast.makeText(MainActivity.this, "btn1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnTwo:
                {
                Toast.makeText(MainActivity.this, "btn2", Toast.LENGTH_SHORT).show();
                   Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);




            }

            default:
                break;
        }

}*/
