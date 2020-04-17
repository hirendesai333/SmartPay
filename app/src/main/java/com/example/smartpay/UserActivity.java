package com.example.smartpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class UserActivity extends AppCompatActivity {

    TextView greetingText,UserName,UserNum,UserEmail;
    ImageView editImg,UserImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        showGreetings();

        UserName = findViewById(R.id.userName);
        UserNum = findViewById(R.id.userNum);
        UserEmail = findViewById(R.id.userEmail);
        UserImage = findViewById(R.id.userImage);

        SharedPreferences sp = getSharedPreferences("mysharedpref", MODE_PRIVATE);
        String name = sp.getString("NAME_KEY", null);
        String num = sp.getString("NUM_KEY", null);
        String email = sp.getString("EMAIL_KEY",null);

        UserName.setText(name);
        UserNum.setText(num);
        UserEmail.setText(email);

        editImg = findViewById(R.id.editProfile);
        editImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(getApplicationContext(),ProfileEditActivity.class);
                startActivity(profileIntent);
            }
        });

        /*firebaseAuth = FirebaseAuth.getInstance();
        userRef = FirebaseDatabase.getInstance().getReference("Users");

        saveInformation();*/

    }

    /*private void saveInformation() {
        String name = UserName.getText().toString();
        String number = UserNum.getText().toString();
        String email = UserEmail.getText().toString();

        UserInformation userInformation = new UserInformation(name,number,email);
        userRef.child(name).setValue(userInformation);
    }*/

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
