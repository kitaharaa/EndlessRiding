package com.kitaharaa.endlessriding.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.os.Bundle;
import android.webkit.WebView;

import com.kitaharaa.endlessriding.R;

public class MainActivity extends AppCompatActivity {
    Boolean GAME_PASS = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashScreen.installSplashScreen(this);

        if (GAME_PASS) {
            setContentView(R.layout.activity_main_web_view);
            WebView webView = findViewById(R.id.webView);
            webView.loadUrl("https://tinypng.com/");
        } else {
            setContentView(R.layout.activity_main);
            getSupportActionBar().hide();
        }
    }
}