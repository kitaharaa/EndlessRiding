package com.kitaharaa.endlessriding.game;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.kitaharaa.endlessriding.R;
import com.kitaharaa.endlessriding.result.ResultActivity;

/* There we can draw and move elements */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private final MainThread thread;
    private final Context activity;
    private final int screenWidth;
    private final int screenHeight;
    private final Paint paint;
    private Car car;
    private Background background;
    private boolean isGameOver = false;
    private Obstacle firstObstacle, secondObstacle, thirdObstacle;
    private final int firstObstacleVelocity = 25;
    private final int secondObstacleVelocity = 20;
    private final int thirdObstacleVelocity = 15;

    /* Constructor */
    public GameView(GameActivity activity) {
        super(activity);
        this.activity = activity;
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);
    }

    /* When class creates */
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        getHolder().addCallback(this);
        setFocusable(true);

        createObjects();

        thread.setRunning(true);
        thread.start();
    }

    /* When class changes*/
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        update();
    }

    /*When destroy */
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        thread.setRunning(false);
    }

    /* Creating of objects*/
    public void createObjects() {
        car = new Car(BitmapFactory.decodeResource(getResources(), R.drawable.car));
        background = new Background((BitmapFactory.decodeResource(getResources(), R.drawable.game_background)),
                screenWidth);
        firstObstacle = new Obstacle((BitmapFactory.decodeResource(getResources(), R.drawable.obstacle)),
                screenWidth, screenHeight, firstObstacleVelocity);
        secondObstacle = new Obstacle((BitmapFactory.decodeResource(getResources(), R.drawable.obstacle)),
                screenWidth, screenHeight, secondObstacleVelocity);
        thirdObstacle = new Obstacle((BitmapFactory.decodeResource(getResources(), R.drawable.obstacle)),
                screenWidth, screenHeight, thirdObstacleVelocity);
    }

    /* When update */
    public void update() {
        car.update();
        firstObstacle.update();
        secondObstacle.update();
        thirdObstacle.update();

        checkIfContinueGame();
    }

    /* Check is gae allowed to continue */
    private void checkIfContinueGame() {
        if ((car.getX() - firstObstacle.getX() > 0 && car.getX() - firstObstacle.getX() < firstObstacle.getImageWidth()
                && car.getY() - firstObstacle.getY() < firstObstacle.getImageHeight()) ||

                (car.getX() - firstObstacle.getX() < 0 && firstObstacle.getX() - car.getX() < car.getImageWidth()
                        && car.getY() - firstObstacle.getY() < firstObstacle.getImageHeight()) ||

                (car.getX() - secondObstacle.getX() > 0 && car.getX() - secondObstacle.getX() < secondObstacle.getImageWidth()
                        && car.getY() - secondObstacle.getY() < secondObstacle.getImageHeight()) ||

                (car.getX() - secondObstacle.getX() < 0 && secondObstacle.getX() - car.getX() < car.getImageWidth()
                        && car.getY() - secondObstacle.getY() < secondObstacle.getImageHeight()) ||

                (car.getX() - thirdObstacle.getX() > 0 && car.getX() - thirdObstacle.getX() < thirdObstacle.getImageWidth()
                        && car.getY() - thirdObstacle.getY() < thirdObstacle.getImageHeight()) ||
                (car.getX() - thirdObstacle.getX() < 0 && thirdObstacle.getX() - car.getX() < car.getImageWidth()
                        && car.getY() - thirdObstacle.getY() < thirdObstacle.getImageHeight())) {
            isGameOver = true;
        }
    }

    /* Count elements */
    public int countScore() {
        return firstObstacle.getScore()
                + secondObstacle.getScore()
                + thirdObstacle.getScore();
    }

    /* Draw elements */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        background.draw(canvas);
        car.draw(canvas);
        firstObstacle.draw(canvas);
        secondObstacle.draw(canvas);
        thirdObstacle.draw(canvas);

        checkIsGameOver(canvas);
    }

    /*Check is game over */
    public void checkIsGameOver(Canvas canvas) {
        if (countScore() > 40 || isGameOver) {
            canvas.drawText("Score: " + countScore(), 100, 100, paint);

            thread.setRunning(false);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }

            activity.startActivity(new Intent(activity, ResultActivity.class).
                    putExtra("score", countScore()));
            System.exit(0);
        }
        canvas.drawText(getResources().getString(R.string.score_text) + countScore(), 100, 100, paint);
    }

    /* When we touch screen action performed */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        car.setX((int) event.getX());

        return true;
    }
}
