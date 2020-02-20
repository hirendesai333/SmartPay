package com.example.smartpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    TextView username;
    TextView userNum;
    public static final String DEFAULT = "N/A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        username = findViewById(R.id.tv_name);
        userNum = findViewById(R.id.user_num);

        SharedPreferences sharedPreferences = getSharedPreferences("MYDATA", MODE_PRIVATE);
        String name = sharedPreferences.getString("name",DEFAULT);
        username.setText("Welcome "+name);

        /*SharedPreferences sharedPreferences2 = getSharedPreferences("MYDATA2", MODE_PRIVATE);
        String num = sharedPreferences2.getString("num",DEFAULT);
        userNum.setText(num);*/

    }
}
