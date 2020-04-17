package com.example.smartpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class TransactionActivity extends AppCompatActivity {

    TextView dateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        dateTime = findViewById(R.id.tvDateTime);
        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        dateTime.setText(currentDateTimeString);

        CardView cardView = findViewById(R.id.cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(TransactionActivity.this);
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottomsheet,
                        (LinearLayout)findViewById(R.id.bottomsheet));
                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();
            }
        });

    }

}
