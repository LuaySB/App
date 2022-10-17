package com.example.app;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SearchFragment extends Fragment implements View.OnClickListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SearchFragment() {}

    private DatabaseReference rootDatabaseref;
    private FirebaseAuth mAuth;

    private Button IcaButton;
    private Button LidlButton;
    private Button CoopKButton;
    private Button CoopVButton;

    private ImageButton IcaLikeButton;
    private ImageButton LidlLikeButton;
    private ImageButton CoopKLikeButton;
    private ImageButton CoopVLikeButton;

    private ImageButton IcaLikedButton;
    private ImageButton LidlLikedButton;
    private ImageButton CoopKLikedButton;
    private ImageButton CoopVLikedButton;

    private String store;

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        IcaButton = view.findViewById(R.id.buttonIca);
        IcaButton.setOnClickListener(this);
        LidlButton = view.findViewById(R.id.buttonLidl);
        LidlButton.setOnClickListener(this);
        CoopKButton = view.findViewById(R.id.buttonCoopK);
        CoopKButton.setOnClickListener(this);
        CoopVButton = view.findViewById(R.id.buttonCoopV);
        CoopVButton.setOnClickListener(this);

        IcaLikeButton = view.findViewById(R.id.likeIca);
        IcaLikeButton.setOnClickListener(this);
        LidlLikeButton = view.findViewById(R.id.likeLidl);
        LidlLikeButton.setOnClickListener(this);
        CoopKLikeButton = view.findViewById(R.id.likeCoopK);
        CoopKLikeButton.setOnClickListener(this);
        CoopVLikeButton = view.findViewById(R.id.likeCoopV);
        CoopVLikeButton.setOnClickListener(this);

        IcaLikedButton = view.findViewById(R.id.likedIca);
        IcaLikedButton.setOnClickListener(this);
        LidlLikedButton = view.findViewById(R.id.likedLidl);
        LidlLikedButton.setOnClickListener(this);
        CoopKLikedButton = view.findViewById(R.id.likedCoopK);
        CoopKLikedButton.setOnClickListener(this);
        CoopVLikedButton = view.findViewById(R.id.likedCoopV);
        CoopVLikedButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        rootDatabaseref = FirebaseDatabase.getInstance().getReference("user")
                .child(mAuth.getCurrentUser().getUid());
        rootDatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("icaFav").exists() && snapshot.child("lidlFav").exists()
                        && snapshot.child("coopvFav").exists() && snapshot.child("coopkFav").exists()){

                    String icaFav = snapshot.child("icaFav").getValue().toString();
                    String lidlFav = snapshot.child("lidlFav").getValue().toString();
                    String coopvFav = snapshot.child("coopvFav").getValue().toString();
                    String coopkFav = snapshot.child("coopkFav").getValue().toString();

                    if(icaFav.equals("YES")){
                        IcaLikeButton.setVisibility(GONE);
                        IcaLikedButton.setVisibility(VISIBLE);
                    }else{
                        IcaLikeButton.setVisibility(VISIBLE);
                        IcaLikedButton.setVisibility(GONE);
                    }

                    if(lidlFav.equals("YES")){
                        LidlLikedButton.setVisibility(VISIBLE);
                        LidlLikeButton.setVisibility(GONE);
                    }else{
                        LidlLikeButton.setVisibility(VISIBLE);
                        LidlLikedButton.setVisibility(GONE);
                    }

                    if(coopvFav.equals("YES")){
                        CoopVLikedButton.setVisibility(VISIBLE);
                        CoopVLikeButton.setVisibility(GONE);
                    }else{
                        CoopVLikeButton.setVisibility(VISIBLE);
                        CoopVLikedButton.setVisibility(GONE);
                    }

                    if(coopkFav.equals("YES")){
                        CoopKLikedButton.setVisibility(VISIBLE);
                        CoopKLikeButton.setVisibility(GONE);
                    }else{
                        CoopKLikeButton.setVisibility(VISIBLE);
                        CoopKLikedButton.setVisibility(GONE);
                    }
                }else{

                    if(!snapshot.child("icaFav").exists())
                        rootDatabaseref.child("icaFav").setValue("NO");

                    if(!snapshot.child("lidlFav").exists())
                        rootDatabaseref.child("lidlFav").setValue("NO");

                    if(!snapshot.child("coopvFav").exists())
                        rootDatabaseref.child("coopvFav").setValue("NO");

                    if(!snapshot.child("coopkFav").exists())
                        rootDatabaseref.child("coopkFav").setValue("NO");

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonIca) {
            Intent intent = new Intent(getActivity(), ProductsActivity.class);
            intent.putExtra("store", "ica");
            startActivity(intent);
            }
        else if(v.getId() == R.id.buttonLidl) {
            Intent intent = new Intent(getActivity(), ProductsActivity.class);
            intent.putExtra("store", "lidl");
            startActivity(intent);
            }
        else if(v.getId() == R.id.buttonCoopK) {
            Intent intent = new Intent(getActivity(), ProductsActivity.class);
            intent.putExtra("store", "coop kronoparken");
            startActivity(intent);
            }
        else if(v.getId() == R.id.buttonCoopV) {
            Intent intent = new Intent(getActivity(), ProductsActivity.class);
            intent.putExtra("store", "coop välsviken");
            startActivity(intent);
            }
        else if(v.getId() == R.id.likeIca){
            IcaLikeButton.setVisibility(GONE);
            IcaLikedButton.setVisibility(VISIBLE);
            rootDatabaseref.child("icaFav").setValue("YES");
            }
        else if(v.getId() == R.id.likeLidl){
            LidlLikeButton.setVisibility(GONE);
            LidlLikedButton.setVisibility(VISIBLE);
            rootDatabaseref.child("lidlFav").setValue("YES");
            }
        else if(v.getId() == R.id.likeCoopK){
            CoopKLikeButton.setVisibility(GONE);
            CoopKLikedButton.setVisibility(VISIBLE);
            rootDatabaseref.child("coopkFav").setValue("YES");
            }
        else if(v.getId() == R.id.likeCoopV){
            CoopVLikeButton.setVisibility(GONE);
            CoopVLikedButton.setVisibility(VISIBLE);
            rootDatabaseref.child("coopvFav").setValue("YES");
            }
        else if(v.getId() == R.id.likedIca){
            IcaLikeButton.setVisibility(VISIBLE);
            IcaLikedButton.setVisibility(GONE);
            rootDatabaseref.child("icaFav").setValue("NO");
            }
        else if(v.getId() == R.id.likedLidl){
            LidlLikeButton.setVisibility(VISIBLE);
            LidlLikedButton.setVisibility(GONE);
            rootDatabaseref.child("lidlFav").setValue("NO");
            }
        else if(v.getId() == R.id.likedCoopK){
            CoopKLikeButton.setVisibility(VISIBLE);
            CoopKLikedButton.setVisibility(GONE);
            rootDatabaseref.child("coopkFav").setValue("NO");
            }
        else if(v.getId() == R.id.likedCoopV){
            CoopVLikeButton.setVisibility(VISIBLE);
            CoopVLikedButton.setVisibility(GONE);
            rootDatabaseref.child("coopvFav").setValue("NO");
        }
    }
}