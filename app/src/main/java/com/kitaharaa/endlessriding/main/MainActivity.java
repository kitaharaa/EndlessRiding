package com.kitaharaa.endlessriding.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.kitaharaa.endlessriding.R;
import com.kitaharaa.endlessriding.game.GameActivity;

/* Main Activity */
public class MainActivity extends Activity
        implements MainContract.View {

    private MainContract.Presenter presenter;
    Boolean GAME_PASS;
    String WEB_LINK;
    WebView webView;
    public ValueCallback<Uri[]> uploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;

    @SuppressLint("SetJavaScriptEnabled")
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

            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setAllowFileAccess(true);
            webSettings.setDomStorageEnabled(true);

            webView.setWebChromeClient(new WebChromeClient() {

                public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback,
                                                 WebChromeClient.FileChooserParams fileChooserParams) {
                    if (uploadMessage != null) {
                        uploadMessage.onReceiveValue(null);
                        uploadMessage = null;
                    }

                    uploadMessage = filePathCallback;

                    Intent intent = fileChooserParams.createIntent();

                    try {
                        startActivityForResult(intent, REQUEST_SELECT_FILE);
                    } catch (ActivityNotFoundException e) {
                        uploadMessage = null;
                        return false;
                    }
                    return true;
                }
            });
            webView.loadUrl(WEB_LINK);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_SELECT_FILE) {
            if (uploadMessage == null)
                return;
            uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
            uploadMessage = null;
        }
    }

    /* Create listener to buttons */
    @Override
    public void createButtonListener() {
        Button startGameButton = findViewById(R.id.start_button);
        startGameButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            startActivity(intent);
            finish();
        });
    }

    /* When back is pressed */
    @Override
    public void onBackPressed() {
        if (!GAME_PASS) {
            Toast.makeText(this, getResources().getString(R.string.not_allowed_text), Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
    }
}

