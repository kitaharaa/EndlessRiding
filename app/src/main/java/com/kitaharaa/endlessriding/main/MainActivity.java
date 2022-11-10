package com.kitaharaa.endlessriding.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.kitaharaa.endlessriding.R;
import com.kitaharaa.endlessriding.game.GameActivity;

public class MainActivity extends Activity
        implements MainContract.View {
    private MainContract.Presenter presenter;
    Boolean GAME_PASS;
    String WEB_LINK;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MainPresenter(this);
        GAME_PASS = presenter.getGamePass();
        WEB_LINK = presenter.getWebLink();

        if (GAME_PASS) {
            setContentView(R.layout.activity_main);
            createButtonListener();
        } else {
            setContentView(R.layout.activity_main_web_view);
            webView = findViewById(R.id.webView);
            webView.loadUrl(WEB_LINK);
        }
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

    @Override
    public void onBackPressed() {
        if(!GAME_PASS) {
            Toast.makeText(this, "It is just not impossible", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
    }
}
