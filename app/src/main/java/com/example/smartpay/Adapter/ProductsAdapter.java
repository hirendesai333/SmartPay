package com.example.smartpay.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpay.Dto.Product;
import com.example.smartpay.R;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>{

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
        Product product = productList.get(position);

        holder.textViewName.setText(product.getItemName());
        holder.textViewQty.setText(product.getQty());
        holder.textViewWeight.setText(product.getWeight());
        holder.textViewPrice.setText(product.getPrice());
        holder.textViewPaymentRefNo.setText(product.getPaymentRefNo());
        holder.textViewTotalPrice.setText(product.getTotalPrice());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewQty, textViewWeight, textViewPrice,textViewPaymentRefNo,textViewTotalPrice;

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
