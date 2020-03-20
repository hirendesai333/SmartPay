package com.example.smartpay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.github.chrisbanes.photoview.PhotoView;

public class IndoorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_indoor);

        PhotoView photoView = findViewById(R.id.photo_view);
        photoView.setImageResource(R.drawable.map);

    }

}
