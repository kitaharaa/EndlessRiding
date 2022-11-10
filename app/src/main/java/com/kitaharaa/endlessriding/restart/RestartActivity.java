package com.kitaharaa.endlessriding.restart;

import android.app.Activity;
import android.os.Bundle;

import com.kitaharaa.endlessriding.R;

public class RestartActivity extends Activity implements RestartContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restart);
    }
}