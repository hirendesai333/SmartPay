package com.example.smartpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.util.Calendar;

public class UserActivity extends AppCompatActivity {

    TextView greetingText;
    TextView UserName;
    TextView UserNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        showGreetings();

        UserName = findViewById(R.id.userName);
        UserNum = findViewById(R.id.userNum);

        SharedPreferences sp = getSharedPreferences("mysharedpref", MODE_PRIVATE);
        String name = sp.getString("NAME_KEY", null);
        String num = sp.getString("NUM_KEY", null);

        UserName.setText(name);
        UserNum.setText(num);

    }

    private void showGreetings() {
        greetingText = findViewById(R.id.greetingText);
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
            greetingText.setText("Good Morning");
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            greetingText.setText("Good Afternoon");
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            greetingText.setText("Good Evening");
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            greetingText.setText("Good Night");
        }
    }
}
