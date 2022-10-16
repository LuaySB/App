package com.example.notespro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllItemsAdapter extends RecyclerView.Adapter<AllItemsAdapter.AllItemsViewHolder> {

    Context context;
    ArrayList<Product> productArrayList;



    public AllItemsAdapter(Context context, ArrayList<Product> productArrayList){

        this.context = context;
        this.productArrayList = productArrayList;

    }

    @NonNull
    @Override
    public AllItemsAdapter.AllItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item,parent, false);
        return new AllItemsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AllItemsAdapter.AllItemsViewHolder holder, int position) {

        Product product = productArrayList.get(position);

        holder.kategori.setText(product.kategori);
        holder.name.setText(product.offer);
        holder.pre.setText(product.pre);
        holder.price.setText(product.price);
        holder.store.setText(product.store);

    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public static class AllItemsViewHolder extends RecyclerView.ViewHolder{

        TextView name, price, store, pre, kategori;


        public AllItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            kategori = itemView.findViewById(R.id.product_kategori);
            name = itemView.findViewById(R.id.product_name);
            pre = itemView.findViewById(R.id.product_pre);
            price = itemView.findViewById(R.id.product_price);
            store = itemView.findViewById(R.id.product_store);
        }
    }

}
