package com.example.app;

import android.app.Application;

public class Language extends Application {

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    int language;

}