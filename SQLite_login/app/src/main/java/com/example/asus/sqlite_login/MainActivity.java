package com.example.asus.sqlite_login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button sign;
    private Button login;
    private EditText name;
    private EditText pwd;
    private TextView message;
    private List<User> userList;
    private List<User> dataList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sign=(Button)findViewById(R.id.btn_sign);
        login=(Button)findViewById(R.id.btn_login);
        name=(EditText)findViewById(R.id.edit_name);
        pwd=(EditText)findViewById(R.id.edit_pwd);
        message=(TextView)findViewById(R.id.txt_message);
        //处理注册事件
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp_name=name.getText().toString().trim();
                String temp_pwd=pwd.getText().toString().trim();
                if(!temp_name.equals("")&&!temp_pwd.equals("")){
                    User user=new User();
                    user.setUserpwd(temp_pwd);
                    user.setUsername(temp_name);
                    int result=SqliteDB.getInstance(getApplicationContext()).saveUser(user);
                    if(result==1){
                        message.setText("注册成功!");
                    }else if(result==-1){
                        message.setText("用户名已经存在!");
                    }else {
                        message.setText("!");
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"请输入用户名和密码",Toast.LENGTH_LONG).show();
                }
            }
        });

        //处理登陆事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp_name=name.getText().toString().trim();
                String temp_pwd=pwd.getText().toString().trim();
                if(!temp_name.equals("")&&!temp_pwd.equals("")){
                    int result=SqliteDB.getInstance(getApplicationContext()).Quer(temp_pwd,temp_name);
                    if (result==1)
                    {
                        message.setText("登录成功！");
                    }
                    else if (result==0){
                        message.setText("用户名不存在！");

                    }
                    else if(result==-1)
                    {
                        message.setText("密码错误！");
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"请输入用户名和密码",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
