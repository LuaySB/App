package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    FirebaseAuth mAuth;
    ImageButton logOutButton, helpBtn, notificationBtn;
    Switch changeFromSwedishToEnglish;
    TextView homeTitle, welcomeText, welcomeMessage;

    public HomeFragment() {}

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        homeTitle = view.findViewById(R.id.homeTitle);
        welcomeText = view.findViewById(R.id.welcomeText);
        welcomeMessage = view.findViewById(R.id.welcomeMessage);

        logOutButton = view.findViewById(R.id.logOutBtn);
        helpBtn = view.findViewById(R.id.help_btn);
        notificationBtn = view.findViewById(R.id.notification_btn);

        logOutButton.setOnClickListener(this);
        helpBtn.setOnClickListener((v)-> startActivity(new Intent(getContext(), QuestionsActivity.class)));
        notificationBtn.setOnClickListener((v)-> getActivity().startService(new Intent(this.getActivity(), NotificationHandler.class)));

        /* To change language from Swedish -> English START */
        changeFromSwedishToEnglish = view.findViewById(R.id.switchToEnglish);
        changeFromSwedishToEnglish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (changeFromSwedishToEnglish.isChecked()) {
                    homeTitle.setText("Home");
                    welcomeText.setText("Welcome!");
                    welcomeMessage.setText("This app shows you what offers are available locally in your stores in Karlstad. You can click on the icons in the menu bar below and start looking for offers!");

                }
                else {
                    homeTitle.setText("Hem");
                    welcomeText.setText("Välkommen!");
                    welcomeMessage.setText("Denna app är designad för dig som vill se vilka erbjudande som finns i dina butiker lokalt i Karlstad. Du kan klicka på ikonerna i meny-baren under och börja leta erbjudanden!!");
                }
            }
        });

        /* To change language from Swedish -> English END */

        return view;
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.logOutBtn) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getContext(), LoginActivity.class));
        }
        else
            return;
    }


    public Boolean getSwitchStatus() {
        if(changeFromSwedishToEnglish.isChecked()) {
            return true;
        }
        else {
            return false;
        }
    }
}