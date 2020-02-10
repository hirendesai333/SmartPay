package com.example.smartpay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.example.smartpay.Adapter.CustomListAdapter;
import com.example.smartpay.Dto.AddListItem;
import com.example.smartpay.Dto.ListItem;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.shreyaspatil.EasyUpiPayment.EasyUpiPayment;
import com.shreyaspatil.EasyUpiPayment.listener.PaymentStatusListener;
import com.shreyaspatil.EasyUpiPayment.model.TransactionDetails;

import java.util.ArrayList;


public class ScanBarcode extends AppCompatActivity implements PaymentStatusListener {

    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<ListItem> list = new ArrayList<>();
    ArrayList<AddListItem> results = new ArrayList<>();


    Button scanAgainBtn;
    Button checkoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_item);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("itemFruits");

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ListItem value = dataSnapshot.getValue(ListItem.class);
                list.add(value);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        new IntentIntegrator(this)
                .setOrientationLocked(false)
                .initiateScan();

        scanAgainBtn = findViewById(R.id.scanAgainBtn);
        checkoutBtn = findViewById(R.id.checkoutBtn);

        scanAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(ScanBarcode.this).setOrientationLocked(false).initiateScan();
            }
        });

        final EasyUpiPayment easyUpiPayment = new EasyUpiPayment.Builder()
                .with(ScanBarcode.this)
                .setPayeeVpa("9408453375@upi")
                .setPayeeName("hp")
                .setTransactionId("001016264631")
                .setTransactionRefId("CICAgKDu8OKETA")
                .setDescription(":)")
                .setAmount("1.00")
                .build();

        checkoutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                easyUpiPayment.startPayment();

            }
        });
    }

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                String strScanFruitName = result.getContents().toString();
                Log.d("Size ---> ", String.valueOf(list.size()));
                if (list.size() > 0) {
                    Log.d("If ---> ", "Ok");

                    for (int i = 0; i < list.size(); i++) {
                        Log.d("If For---> ", "Ok");

                        String strFruitName = list.get(i).getName();
                        if (strFruitName.equals(strScanFruitName)) {
                            Log.d("If For If---> ", "Ok");

                            /*Toast.makeText(this, "Name ---> " + list.get(i).getName() + "\n"
                                    + "Price ---> " + list.get(i).getPrice() + "\n"
                                    + "Weight ---> " + list.get(i).getWeight(), Toast.LENGTH_SHORT).show();*/
                            if (results.size() > 0) {
                                for (int j = 0; j < results.size(); j++) {
                                    Log.d("Position ---> ", String.valueOf(j));
                                    String strName = results.get(j).getName();
                                    Log.d("STR NAME ---> ", strName);
                                    if (strName.equals(strScanFruitName)) {
                                        int indexOf = j + 1;
                                        Log.d("Index Of ---> ", String.valueOf(indexOf));

                                        int iQty = Integer.parseInt(results.get(j).getQty());
                                        int newQty = iQty + 1;

                                        AddListItem user1 = new AddListItem();
                                        user1.setName(list.get(i).getName());
                                        user1.setPrice(list.get(i).getPrice());
                                        user1.setWeight(list.get(i).getWeight());
                                        user1.setQty(String.valueOf(newQty));
                                        user1.setImage(list.get(i).getImage());
//                                    results.add(user1);

                                        results.set(j, user1);
                                        ArrayList userList = results;
                                        final ListView lv = findViewById(R.id.listView);
                                        lv.setAdapter(new CustomListAdapter(this, userList));
                                        break;

                                    } else {
                                        if (results.size() == (j + 1)) {
                                            Log.d("Else ---> ", "ok");

                                            AddListItem user1 = new AddListItem();
                                            user1.setName(list.get(i).getName());
                                            user1.setPrice(list.get(i).getPrice());
                                            user1.setWeight(list.get(i).getWeight());
                                            user1.setQty("1");
                                            user1.setImage(list.get(i).getImage());
                                            results.add(user1);

                                            ArrayList userList = results;
                                            final ListView lv = findViewById(R.id.listView);
                                            lv.setAdapter(new CustomListAdapter(this, userList));
                                            break;
                                        }

                                    }
                                }


                            } else {
                                AddListItem user1 = new AddListItem();
                                user1.setName(list.get(i).getName());
                                user1.setPrice(list.get(i).getPrice());
                                user1.setWeight(list.get(i).getWeight());
                                user1.setQty("1");
                                user1.setImage(list.get(i).getImage());
                                results.add(user1);

                                ArrayList userList = results;
                                final ListView lv = findViewById(R.id.listView);
                                lv.setAdapter(new CustomListAdapter(this, userList));
                                break;

                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @Override
    public void onTransactionCompleted(TransactionDetails transactionDetails) {
        // Transaction Completed
        Log.d("TransactionDetails", transactionDetails.toString());
    }

    @Override
    public void onTransactionSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionSubmitted() {
        Toast.makeText(this, "Pending | Submitted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionFailed() {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTransactionCancelled() {
        Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAppNotFound() {
        Toast.makeText(this, "App Not Found", Toast.LENGTH_SHORT).show();
    }
}
