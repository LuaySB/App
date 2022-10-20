package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

public class QuestionsActivity extends AppCompatActivity {

    Switch changeFromSwedishToEnglish;
    ImageButton goBack;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        webView = findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/questionsSvenska.html");

        goBack = findViewById(R.id.backBtn_questions);
        goBack.setOnClickListener(v->finish());
        changeFromSwedishToEnglish = findViewById(R.id.switchToEnglish_questions);
        changeFromSwedishToEnglish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (changeFromSwedishToEnglish.isChecked()) {
                    webView.loadUrl("file:///android_asset/questionsEnglish.html");
                    changeFromSwedishToEnglish.setText("Svenska");
                }
                else {
                    webView.loadUrl("file:///android_asset/questionsSvenska.html");
                    changeFromSwedishToEnglish.setText("English");
                }
            }
        });
    }
}