package com.example.smartpay.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpay.Dto.Product;
import com.example.smartpay.R;
import com.example.smartpay.ViewActivity;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    Context mCtx;
    List<Product> productList;

    public ProductsAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.product_list_item,
                parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, ViewActivity.class);
                mCtx.startActivity(intent);
            }
        });

        Product product = productList.get(position);

//        holder.textViewName.setText(product.getItemName());
//        holder.textViewQty.setText(product.getQty()+""+product.getWeight());
//        holder.textViewPrice.setText("₹ "+product.getPrice());
        holder.textViewTotalPrice.setText(String.valueOf(product.getTotalPrice()));
        holder.textViewPaymentRefNo.setText(product.getPaymentRefNo());


//        for (int i = 0; i < productList.size(); i++) {
//            Product product = productList.get(i);
//            String strFirstRefNum = productList.get(0).getPaymentRefNo();
//            if (i == 0) {
//                Log.d("Start --->", "1");
//                holder.textViewTotalPrice.setText(String.valueOf(productList.get(0).getTotalPrice()));
//                holder.textViewPaymentRefNo.setText(productList.get(0).getPaymentRefNo());
//            } else {
//                Log.d("Start --->", "2");
//                if (i > 0) {
//                    Log.d("Start --->", "21");
//
//                    for (int j = 1; j < productList.size(); j++) {
//                        Log.d("Start --->", "3");
//
//                        String strRefNum = product.getPaymentRefNo();
//                        if (strRefNum.equals(strFirstRefNum)) {
//                            Log.d("Start --->", "4");
//
//                        } else {
//                            Log.d("Start --->", "5");
//
//                            if (productList.size() == (j + 1)) {
//                                Log.d("Start --->", "6");
//
//                                holder.textViewTotalPrice.setText(String.valueOf(product.getTotalPrice()));
//                                holder.textViewPaymentRefNo.setText(product.getPaymentRefNo());
//                            } else {
//                                Log.d("Start --->", "7");
//
//                                if (productList.size() == (j + 1)) {
//                                    Log.d("Start --->", "8");
//
//                                    holder.textViewTotalPrice.setText(String.valueOf(product.getTotalPrice()));
//                                    holder.textViewPaymentRefNo.setText(product.getPaymentRefNo());
//                                }
//                            }
//                        }
//                    }
//                } else {
//                    Log.d("Start --->", "9");
//
//                    if (productList.size() == (i + 1)) {
//                        Log.d("Start --->", "10");
//
//                        holder.textViewTotalPrice.setText(String.valueOf(product.getTotalPrice()));
//                        holder.textViewPaymentRefNo.setText(product.getPaymentRefNo());
//                    } else {
//                        Log.d("Start --->", "7");
//
//                        if (productList.size() == (i + 1)) {
//                            Log.d("Start --->", "11");
//
//                            holder.textViewTotalPrice.setText(String.valueOf(product.getTotalPrice()));
//                            holder.textViewPaymentRefNo.setText(product.getPaymentRefNo());
//                        }
//                    }
//                }
//            }
//
//
//        }


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewQty, textViewWeight, textViewPrice, textViewPaymentRefNo, textViewTotalPrice;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.tv_itemName);
            textViewQty = itemView.findViewById(R.id.tv_itemQty);
            textViewWeight = itemView.findViewById(R.id.tv_itemWeight);
            textViewPrice = itemView.findViewById(R.id.tv_itemPrice);
            textViewPaymentRefNo = itemView.findViewById(R.id.tv_paymentRefNo);
            textViewTotalPrice = itemView.findViewById(R.id.tv_totalPrice);

        }
    }

}
