package com.example.smartpay.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartpay.Dto.AddListItem;
import com.example.smartpay.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    private ArrayList<AddListItem> listData;
    private LayoutInflater layoutInflater;

    public CustomListAdapter(Context aContext, ArrayList<AddListItem> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View v, ViewGroup vg) {

        ViewHolder holder;

        if (v == null) {
            v = layoutInflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();
            holder.uName =v.findViewById(R.id.name);
            holder.uPrice =v.findViewById(R.id.price);
            holder.uWeight =v.findViewById(R.id.weight);
            holder.uImage = v.findViewById(R.id.imageView);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        double price = Double.parseDouble((listData.get(position).getPrice()));
        double qty = Double.parseDouble((listData.get(position).getQty()));

        double totalPrice = price * qty;

        holder.uName.setText(listData.get(position).getName());
        holder.uPrice.setText("₹"+listData.get(position).getPrice());
        holder.uWeight.setText(listData.get(position).getQty() + " " + listData.get(position).getWeight());
        Picasso.get().load(listData.get(position).getImage()).into(holder.uImage);

        return v;

    }

    static class ViewHolder {
        TextView uName;
        TextView uPrice;
        TextView uWeight;
        ImageView uImage;
    }

}
