package com.example.smartpay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.smartpay.Adapter.ProductsAdapter;
import com.example.smartpay.Dto.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TransactionActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductsAdapter adapter;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        SharedPreferences sp = getSharedPreferences("mysharedpref", MODE_PRIVATE);
        final String num = sp.getString("NUM_KEY", null);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList();

        DatabaseReference dbProducts = FirebaseDatabase.getInstance().getReference("orderedData").child(num);
//        DatabaseReference dbProducts = FirebaseDatabase.getInstance().getReference("orderedData");
        DatabaseReference dbProductChild = dbProducts.child("8980934200");
        DatabaseReference dbProductChild1 = dbProductChild.child("012020000066");

        dbProductChild1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            Log.d("Count --->" , String.valueOf(dataSnapshot.getChildrenCount()));
            Log.d("Child --->" , String.valueOf(dataSnapshot.getChildren()));
                if (dataSnapshot.exists()) {
                    for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                        Log.d("Count 1 --->" , String.valueOf(productSnapshot.getChildrenCount()));

                        Product p = productSnapshot.getValue(Product.class);
                        productList.add(p);

                    }
                }

                adapter = new ProductsAdapter(TransactionActivity.this, productList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });

    }
}