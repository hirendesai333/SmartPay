package com.example.smartpay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {
    private Context context;
    private ProductsAdapter productsAdapter;

    public void setConfig(RecyclerView recyclerView, Context mcContext,List<Product> products, List<String> keys){
        context = mcContext;
        productsAdapter = new ProductsAdapter(products,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(productsAdapter);
    }

    class ProductItemView extends RecyclerView.ViewHolder {
        private TextView ItemName;
        private TextView Price;
        private TextView Weight;
        private TextView Qty;
        private TextView TotalPrice;
        private TextView ItemImage;
        private TextView PaymentRefNo;

        private String key;

        public ProductItemView(ViewGroup parent){
            super(LayoutInflater.from(context).
                    inflate(R.layout.product_list_item, parent, false));

            ItemName = (TextView) itemView.findViewById(R.id.tv_itemName);
            Price = (TextView) itemView.findViewById(R.id.tv_itemPrice);
            Weight = (TextView) itemView.findViewById(R.id.tv_itemWeight);
            Qty = (TextView) itemView.findViewById(R.id.tv_itemQty);
            TotalPrice = (TextView) itemView.findViewById(R.id.tv_totalPrice);
            ItemImage = (TextView) itemView.findViewById(R.id.tv_itemImage);
//            PaymentRefNo = (TextView) itemView.findViewById(R.id.tv_paymentRefNo);

        }

        public void bind(Product product, String key){
            ItemName.setText(product.getItemName());
            Price.setText(product.getPrice());
            Weight.setText(product.getWeight());
            Qty.setText(product.getQty());
            TotalPrice.setText(product.getTotalPrice());
            ItemImage.setText(product.getItemImage());
//            PaymentRefNo.setText(product.getPaymentRefNo());

            this.key = key;
        }
    }

    class ProductsAdapter extends RecyclerView.Adapter<ProductItemView>{
        private List<Product> productList;
        private List<String> keys;

        public ProductsAdapter(List<Product> productList, List<String> keys) {
            this.productList = productList;
            this.keys = keys;
        }

        @NonNull
        @Override
        public ProductItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ProductItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductItemView holder, int position) {
            holder.bind(productList.get(position), keys.get(position));
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }
    }

}
