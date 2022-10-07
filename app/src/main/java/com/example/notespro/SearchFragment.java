package com.example.notespro;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements View.OnClickListener{


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }
    Button IcaButton;
    Button LidlButton;
    Button CoopKButton;
    Button CoopVButton;
    String store;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        }else
            return;
}
}