package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<Product> products;

    public void setFilteredList(List<Product> filteredList){
        this.products = (ArrayList<Product>) filteredList;
        notifyDataSetChanged();
    }

    public MyAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item,parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        Product product = products.get(position);

        //Sänd data från klass-objekt till view-objekt
        holder.name.setText(product.offer);
        holder.pre.setText(product.pre);
        holder.store.setText(product.store);

        //Korregeringar Kategori
        if(product.kategori.equals("None")){
            holder.kategori.setText("Övrigt");
        }else{
            holder.kategori.setText(product.kategori);
        }

        //Korregeringar Pris
        if(product.price.endsWith(":-") || product.price.endsWith("kr")){
            holder.price.setText(product.price);
        }else{
            holder.price.setText(product.price + ":-");
        }

        //Hämta bild för display
        if(product.store.equals("Coop Kronoparken")){
            Glide.with(context).load("https:" + product.image).into(holder.image);

        }else if(product.store.equals("Stora Coop välsviken")) {
            Glide.with(context).load("https:" + product.image).into(holder.image);

        }else if(product.store.equals("Lidl")) {
            holder.image.setImageResource(R.drawable.lidllogo);

        }else{
            Glide.with(context).load(product.image).into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, price, store, pre, kategori;
        ImageView image;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            kategori = itemView.findViewById(R.id.product_kategori);
            name = itemView.findViewById(R.id.product_name);
            pre = itemView.findViewById(R.id.product_pre);
            price = itemView.findViewById(R.id.product_price);
            store = itemView.findViewById(R.id.product_store);
            image = itemView.findViewById(R.id.product_img);
        }
    }
}
