package com.example.smartpay;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ProfileEditActivity extends AppCompatActivity {

    EditText name,mobile;
    Button saveBtn;
    TextView changeImage;

    private int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        name = findViewById(R.id.nameEt);
        mobile = findViewById(R.id.mobileEt);
        saveBtn = findViewById(R.id.saveBtn);
        changeImage = findViewById(R.id.changeImg);

        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });

    }

    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public void saveChanges(){
        String userNameNote = name.getText().toString();
        String numNote = mobile.getText().toString();
        ImageView imageNote = findViewById(R.id.imageView);

        if (numNote.isEmpty()){
            Toast.makeText(ProfileEditActivity.this, "Fill the details!", Toast.LENGTH_SHORT).show();
        }else {
            Intent homeIntent = new Intent(getApplicationContext(), UserActivity.class);
            SharedPreferences sp = getSharedPreferences("mysharedpref", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("NAME_KEY", userNameNote);
            editor.putString("NUM_KEY",numNote);
            editor.putString("IMG_KEY", String.valueOf(imageNote));
            editor.apply();
            startActivity(homeIntent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                ImageView imageView = findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
