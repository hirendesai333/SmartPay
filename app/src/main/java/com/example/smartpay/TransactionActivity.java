package com.example.smartpay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartpay.Adapter.ProductsAdapter;
import com.example.smartpay.Dto.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransactionActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductsAdapter adapter;
    List<Product> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        SharedPreferences sp = getSharedPreferences("mysharedpref", MODE_PRIVATE);
        String num = sp.getString("NUM_KEY", null);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();

        DatabaseReference dbProducts = FirebaseDatabase.getInstance().getReference("orderedData");

        dbProducts.child(num).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {	

                if (dataSnapshot.exists()) {

                    for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {

                        Product p = productSnapshot.getValue(Product.class);
                        productList.add(p);

                    }

                    adapter = new ProductsAdapter(TransactionActivity.this, productList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }
}