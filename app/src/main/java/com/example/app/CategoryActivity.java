package com.example.app;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Locale;

public class CategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Product> products;
    MyAdapter myAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    String kategori;
    TextView productsTitle;


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

        kategori = getIntent().getStringExtra("kategori");

        db = FirebaseFirestore.getInstance();
        products = new ArrayList<Product>();
        myAdapter = new MyAdapter(CategoryActivity.this, products);

        recyclerView.setAdapter(myAdapter);

        //Sätt titel på sida
        productsTitle = findViewById(R.id.products_title);
        if(kategori.equals("None")){
            productsTitle.setText("Övrigt");
        }else{
            productsTitle.setText(kategori.substring(0, 1).toUpperCase() + kategori.substring(1).toLowerCase());
        }

        EventChangeListener();
    }

    private void EventChangeListener() {

        Log.d("MyTag", "Entering store: " + kategori);

        db.collection("ica").orderBy("kategori").startAt(kategori).endAt(kategori + "\uf8ff")
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
                                Product product = dc.getDocument().toObject(Product.class);
                                product.setPrice(product.getPrice().replaceAll("[^.0123456789]",""));
                                products.add(product);
                            }

                            myAdapter.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();

                        }
                    }
                });

        db.collection("lidl").orderBy("kategori").startAt(kategori).endAt(kategori + "\uf8ff")
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
                                Product product = dc.getDocument().toObject(Product.class);
                                product.setPrice(product.getPrice().replaceAll("[^.0123456789]",""));
                                products.add(product);
                            }

                            myAdapter.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();

                        }
                    }
                });

        db.collection("coop välsviken").orderBy("kategori").startAt(kategori).endAt(kategori + "\uf8ff")
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
                                Product product = dc.getDocument().toObject(Product.class);
                                product.setPrice(product.getPrice().replaceAll("[^.0123456789]",""));
                                products.add(product);
                            }

                            myAdapter.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();

                        }
                    }
                });

        db.collection("coop kronoparken").orderBy("kategori").startAt(kategori).endAt(kategori + "\uf8ff")
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
                                Product product = dc.getDocument().toObject(Product.class);
                                product.setPrice(product.getPrice().replaceAll("[^.0123456789]",""));
                                products.add(product);
                            }

                            myAdapter.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();

                        }
                    }
                });
    }
}
