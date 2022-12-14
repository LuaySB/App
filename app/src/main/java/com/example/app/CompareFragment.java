package com.example.app;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class CompareFragment extends Fragment{

    RecyclerView productList;
    ArrayList<Product> products;
    MyAdapter myAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    String[] stores = {"coop kronoparken", "coop välsviken", "ica", "lidl"};

    SearchView searchview;

    TextView mainTitle, produktTitle;
    Switch changeFromSwedishToEnglish;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public CompareFragment() {}

    public static CompareFragment newInstance(String param1, String param2) {
        CompareFragment fragment = new CompareFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_compare, container, false);
        View view2 = inflater.inflate(R.layout.item, container, false);

        mainTitle = view.findViewById(R.id.text_view);
        produktTitle = view2.findViewById(R.id.product_name1);


        searchview = view.findViewById(R.id.searchView);
        searchview.clearFocus();
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        progressDialog = new ProgressDialog(this.getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        productList = (RecyclerView) view.findViewById(R.id.product_list);
        productList.setHasFixedSize(true);
        productList.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        db = FirebaseFirestore.getInstance();
        products = new ArrayList<Product>();
        myAdapter = new MyAdapter(CompareFragment.this.getContext(), products);

        productList.setAdapter(myAdapter);

        EventChangeListener();

        /* To change language from Swedish -> English START */
        changeFromSwedishToEnglish = view.findViewById(R.id.switch2);
        changeFromSwedishToEnglish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (changeFromSwedishToEnglish.isChecked()) {
                    produktTitle.setText("Product");
                    mainTitle.setText("Search after products");
                    changeFromSwedishToEnglish.setText("Svenska");
                }
                else {
                    produktTitle.setText("Produkt");
                    mainTitle.setText("Sök efter produkter");
                    changeFromSwedishToEnglish.setText("English");
                }
            }
        });

        return view;

    }

    private void filterList(String text) {

        List<Product> filteredList = new ArrayList<>();
        for(Product product: products){
            if(product.getOffer().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(product);
            }
        }

        if(filteredList.isEmpty())
            Toast.makeText(this.getActivity(), "Hitta inga varor", Toast.LENGTH_SHORT).show();
        myAdapter.setFilteredList(filteredList);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

    }


    private void EventChangeListener() {

        for(String store: stores){
            Log.d("MyTag", store);

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
                                    products.add(dc.getDocument().toObject(Product.class));
                                }

                                myAdapter.notifyDataSetChanged();
                                if(progressDialog.isShowing())
                                    progressDialog.dismiss();

                            }
                        }
                    });
        }
    }
}