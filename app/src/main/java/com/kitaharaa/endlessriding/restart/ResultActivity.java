package com.kitaharaa.endlessriding.restart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.kitaharaa.endlessriding.R;
import com.kitaharaa.endlessriding.game.GameActivity;
public class ResultActivity extends Activity implements ResultContract.View {
    private ResultPresenter presenter;
    private int scoreCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setScreenPreferences();

        getValueFromIntent();
        setTitleText();
        setScoreValue();

        Button playAgainButton = findViewById(R.id.play_again_button);
        Button exitButton = findViewById(R.id.exit_button);

        playAgainButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, GameActivity.class);
            finish();
            startActivity(intent);
        });

        exitButton.setOnClickListener(view -> {
            finish();
        });

        presenter = new ResultPresenter(this);
    }

    @Override
    public void setScreenPreferences() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_result);
    }

    @Override
    public void setScoreValue() {
        TextView scoreText = findViewById(R.id.score);
        scoreText.setText(getResources().getString(R.string.score_text) + scoreCount);
    }

    @Override
    public void getValueFromIntent() {
        Intent intent = getIntent();
        scoreCount = intent.getIntExtra("score", 0);
    }

    @Override
    public void setTitleText() {
        TextView resultTitle = findViewById(R.id.result_title);

        resultTitle.setText( scoreCount > 40? getResources().getString(R.string.won_text)
                    :getResources().getString(R.string.loose_text));
    }
}