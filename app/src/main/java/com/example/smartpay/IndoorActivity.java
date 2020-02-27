package com.example.smartpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class IndoorActivity extends AppCompatActivity {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<com.mdaftabalam.imageslider.SlidingModel> imageModelArrayList;

    private int[] myImageList = new int[]{R.drawable.mapone, R.drawable.maptwo, R.drawable.mapthree};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indoor);

        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();
        init();
    }

    private ArrayList<com.mdaftabalam.imageslider.SlidingModel> populateList() {
        ArrayList<com.mdaftabalam.imageslider.SlidingModel> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            com.mdaftabalam.imageslider.SlidingModel imageModel = new com.mdaftabalam.imageslider.SlidingModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }
        return list;
    }

    private void init() {
        mPager = findViewById(R.id.pager);
        mPager.setAdapter(new com.mdaftabalam.imageslider.SlidingAdapter(IndoorActivity.this, imageModelArrayList));

        NUM_PAGES = imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, false);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
    }

}
