package com.example.notespro;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.notespro.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());


        binding.bottomNavigationView2.setOnItemSelectedListener(item -> {
            switch(item.getItemId()) {

                case R.id.home:            replaceFragment(new HomeFragment());    break;
                case R.id.search:          replaceFragment(new SearchFragment());  break;
                case R.id.compare:         replaceFragment(new CompareFragment()); break;
                case R.id.list_bulleted:   replaceFragment(new CategoryFragment());    break;
            }
            return true;
        });

    }


    /* Switchar till en ny sida när man klickar på alternativen i menyn längst ner */
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}