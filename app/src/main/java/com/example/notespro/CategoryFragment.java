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
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CategoryFragment() {
        // Required empty public constructor
    }

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        button1 = view.findViewById(R.id.buttonCategory1);
        button1.setOnClickListener(this);
        button2 = view.findViewById(R.id.buttonCategory2);
        button2.setOnClickListener(this);
        button3 = view.findViewById(R.id.buttonCategory3);
        button3.setOnClickListener(this);
        button4 = view.findViewById(R.id.buttonCategory4);
        button4.setOnClickListener(this);
        button5 = view.findViewById(R.id.buttonCategory5);
        button5.setOnClickListener(this);
        button6 = view.findViewById(R.id.buttonCategory6);
        button6.setOnClickListener(this);
        button7 = view.findViewById(R.id.buttonCategory7);
        button7.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonCategory1) {
            Intent intent = new Intent(getActivity(), CategoryActivity.class);
            intent.putExtra("kategori", "FRUKT & GRÖNT");
            startActivity(intent);
        }
        else if (v.getId() == R.id.buttonCategory2) {
            Intent intent = new Intent(getActivity(), CategoryActivity.class);
            intent.putExtra("kategori", "FÄRSKVAROR");
            startActivity(intent);
        }
        else if (v.getId() == R.id.buttonCategory3) {
            Intent intent = new Intent(getActivity(), CategoryActivity.class);
            intent.putExtra("kategori", "MEJERI");
            startActivity(intent);
        }
        else if (v.getId() == R.id.buttonCategory4) {
            Intent intent = new Intent(getActivity(), CategoryActivity.class);
            intent.putExtra("kategori", "KÖTT, FÅGEL OCH FISK");
            startActivity(intent);
        }
        else if (v.getId() == R.id.buttonCategory5) {
            Intent intent = new Intent(getActivity(), CategoryActivity.class);
            intent.putExtra("kategori", "SKAFFERI");
            startActivity(intent);
        }
    }
}