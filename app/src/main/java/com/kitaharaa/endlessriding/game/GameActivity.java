package com.kitaharaa.endlessriding.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/* Activity which run game*/
public class GameActivity extends Activity implements GameActivityContract.View {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setScreenPreferences();

    }

    @Override
    public void setScreenPreferences() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        GameView gameView = new GameView(this);
        setContentView(gameView);
    }
}