package com.kitaharaa.endlessriding.main;

import androidx.core.splashscreen.SplashScreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

import com.kitaharaa.endlessriding.R;
import com.kitaharaa.endlessriding.game.GameActivity;

public class MainActivity extends Activity
        implements MainContract.View  {
    private MainContract.Presenter presenter;
    private int targetFPS = 30;
    Boolean GAME_PASS = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* try {
            Thread.sleep(3000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }*/
        SplashScreen.installSplashScreen(this);

        if (GAME_PASS) {
            setContentView(R.layout.activity_main_web_view);
            WebView webView = findViewById(R.id.webView);
            webView.loadUrl("https://tinypng.com/");
        } else {
            setContentView(R.layout.activity_main);
            createButtonListener();
        }
        presenter = new MainPresenter(this);

    }

    @Override
    public void createButtonListener() {
        Button startGameButton = findViewById(R.id.start_button);
        startGameButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            startActivity(intent);
            finish();
        });
    }
}