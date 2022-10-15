package com.example.notespro;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Products> productsArrayList;
    MyAdapter myAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    String store;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        store = getIntent().getStringExtra("store");

        db = FirebaseFirestore.getInstance();
        productsArrayList = new ArrayList<Products>();
        myAdapter = new MyAdapter(ProductsActivity.this, productsArrayList);

        recyclerView.setAdapter(myAdapter);

        EventChangeListener();
    }

    private void EventChangeListener() {

        Log.d("MyTag", "Entering store: " + store);

        db.collection(store).orderBy("kategori", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null){

                            if(progressDialog.isShowing())
                                progressDialog.dismiss();

                            Log.e("Firestore error!",error.getMessage());
                            return;
                        }

                        for (DocumentChange dc: value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED){
                                Products product = dc.getDocument().toObject(Products.class);
                                product.setPrice(product.getPrice().replaceAll("[^.0123456789]",""));
                                productsArrayList.add(product);
                            }

                            myAdapter.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();

                        }
                    }
                });
    }
}