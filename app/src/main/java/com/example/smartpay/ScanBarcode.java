package com.example.smartpay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartpay.Adapter.CustomListAdapter;
import com.example.smartpay.Dto.AddListItem;
import com.example.smartpay.Dto.ListItem;
import com.example.smartpay.Login.MainActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Date;

public class ScanBarcode extends AppCompatActivity{

    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<ListItem> list = new ArrayList<>();
    ArrayList<AddListItem> results = new ArrayList<>();

    Button scanAgainBtn;
    Button checkoutBtn;

    TextView textViewAmount,dateTime;;

    Double amount;
    String note, name, upiId;
    final int UPI_PAYMENT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_item);

        dateTime = findViewById(R.id.dtTextView);
        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        dateTime.setText(currentDateTimeString);

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
        textViewAmount = findViewById(R.id.textViewAmount);

        scanAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(ScanBarcode.this).setOrientationLocked(false).initiateScan();
            }
        });

    }

    private void pay(Double amount, String upiId, String name, String note) {

        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", String.valueOf(amount))
                .appendQueryParameter("cu", "INR")
                .build();

        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);

        // will always show a dialog to user to choose an app
        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");

        // check if intent resolves
        if (null != chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(ScanBarcode.this, "No UPI app found, please install one to continue", Toast.LENGTH_SHORT).show();
        }

    }

    private void upiPaymentDataOperation(ArrayList<String> data) {
        if (isConnectionAvailable(ScanBarcode.this)) {
            String str = data.get(0);
            Log.d("UPIPAY", "upiPaymentDataOperation: " + str);
            String paymentCancel = "";
            if (str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if (equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    } else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                } else {
                    paymentCancel = "Payment cancelled by user.";

                }
            }

            if (status.equals("success")) {
                //Code to handle successful transaction here.
                Toast.makeText(ScanBarcode.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
                Log.d("UPI", "responseStr: " + approvalRefNo);
            } else if ("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(ScanBarcode.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();



            } else {
                Toast.makeText(ScanBarcode.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(ScanBarcode.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
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
                String strScanFruitName = result.getContents();
                if (list.size() > 0) {
                    double total = 0.0;

                    for (int i = 0; i < list.size(); i++) {

                        String strFruitName = list.get(i).getName();
                        if (strFruitName.equals(strScanFruitName)) {

                            if (results.size() > 0) {
                                for (int j = 0; j < results.size(); j++) {
                                    String strName = results.get(j).getName();
                                    if (strName.equals(strScanFruitName)) {

                                        int iQty = Integer.parseInt(results.get(j).getQty());
                                        int newQty = iQty + 1;

                                        AddListItem user1 = new AddListItem();
                                        user1.setName(list.get(i).getName());
                                        user1.setPrice(list.get(i).getPrice());
                                        user1.setWeight(list.get(i).getWeight());
                                        user1.setQty(String.valueOf(newQty));
                                        user1.setImage(list.get(i).getImage());

                                        results.set(j, user1);
                                        ArrayList userList = results;
                                        final ListView lv = findViewById(R.id.listView);
                                        lv.setAdapter(new CustomListAdapter(this, userList));

                                        break;

                                    } else {
                                        if (results.size() == (j + 1)) {

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

                    if (results.size() > 0) {
                        for (int i = 0; i < results.size(); i++) {
                            double t = Double.parseDouble(results.get(i).getPrice()) * Double.parseDouble(results.get(i).getQty());
                            total = total + t;
                        }

                        textViewAmount.setText("Total amount : ₹" + total);

                        final double finalTotal = total;
                        checkoutBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                amount = finalTotal;
                                upiId = "9408453375@upi";
                                name = "hp";
                                note = "Go ahead & make your day!";
                                pay(amount, upiId, name, note);

                                Log.d("MainActivity","payment");

                            }
                        });
                    }

                }
            }
        }

        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.d("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);
                    } else {
                        Log.d("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    Log.d("UPI", "onActivityResult: " + "Return data is null"); //when user simply back without payment
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_scan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.storeMap:
                Intent searchIntent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(searchIntent);
                return true;
            case R.id.exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.finish();
    }

}
