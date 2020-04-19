package com.example.smartpay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.smartpay.Adapter.RecyclerView_Config;
import com.example.smartpay.Dto.FirebaseDatabaseHelper;
import com.example.smartpay.Dto.Product;

import java.util.List;

public class TransactionActivity extends AppCompatActivity {

    TextView dateTime;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        /*dateTime = findViewById(R.id.tvDateTime);
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
        });*/

        recyclerView = findViewById(R.id.recyclerview_products);
        new FirebaseDatabaseHelper().ReadProducts(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Product> products, List<String> keys) {
                new RecyclerView_Config().setConfig(recyclerView,TransactionActivity.this, products, keys);

            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

    }

}