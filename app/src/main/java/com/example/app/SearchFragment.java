package com.example.app;


import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class SearchFragment extends Fragment implements View.OnClickListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SearchFragment() {}

    Button IcaButton;
    Button LidlButton;
    Button CoopKButton;
    Button CoopVButton;

    ImageButton IcaLikeButton;
    ImageButton LidlLikeButton;
    ImageButton CoopKLikeButton;
    ImageButton CoopVLikeButton;

    ImageButton IcaLikedButton;
    ImageButton LidlLikedButton;
    ImageButton CoopKLikedButton;
    ImageButton CoopVLikedButton;

    String store;

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
            }
        else if(v.getId() == R.id.likeLidl){
            LidlLikeButton.setVisibility(GONE);
            LidlLikedButton.setVisibility(VISIBLE);
            }
        else if(v.getId() == R.id.likeCoopK){
            CoopKLikeButton.setVisibility(GONE);
            CoopKLikedButton.setVisibility(VISIBLE);
            }
        else if(v.getId() == R.id.likeCoopV){
            CoopVLikeButton.setVisibility(GONE);
            CoopVLikedButton.setVisibility(VISIBLE);
            }
        else if(v.getId() == R.id.likedIca){
            IcaLikeButton.setVisibility(VISIBLE);
            IcaLikedButton.setVisibility(GONE);
            }
        else if(v.getId() == R.id.likedLidl){
            LidlLikeButton.setVisibility(VISIBLE);
            LidlLikedButton.setVisibility(GONE);
            }
        else if(v.getId() == R.id.likedCoopK){
            CoopKLikeButton.setVisibility(VISIBLE);
            CoopKLikedButton.setVisibility(GONE);
            }
        else if(v.getId() == R.id.likedCoopV){
            CoopVLikeButton.setVisibility(VISIBLE);
            CoopVLikedButton.setVisibility(GONE);
        }else
            return;
    }
}