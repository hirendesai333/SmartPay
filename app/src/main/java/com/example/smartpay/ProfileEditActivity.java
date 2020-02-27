package com.example.smartpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class ProfileEditActivity extends AppCompatActivity {

    EditText email,mobile;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        email = findViewById(R.id.emailET);
        mobile = findViewById(R.id.mobileEt);
        saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String emailNote = email.getText().toString();
                 String numNote = mobile.getText().toString();

                if (emailNote.isEmpty() || numNote.isEmpty()){
                    Toast.makeText(ProfileEditActivity.this, "Fill the details!", Toast.LENGTH_SHORT).show();
                }else {
                    Intent homeIntent = new Intent(getApplicationContext(), UserActivity.class);
                    SharedPreferences sp = getSharedPreferences("mysharedpref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("EMAIL_KEY", emailNote);
                    editor.putString("NUM_KEY",numNote);
                    editor.apply();
                    startActivity(homeIntent);
                    finish();
                }
            }
        });

    }
}
