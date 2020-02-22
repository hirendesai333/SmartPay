package com.example.smartpay;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class UserActivity extends AppCompatActivity {

    TextView greetingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        showGreetings();

       /* ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("My Profile");
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.address)));
*/


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
