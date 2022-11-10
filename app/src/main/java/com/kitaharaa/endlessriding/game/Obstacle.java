package com.kitaharaa.endlessriding.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/* Class that creates an obstacles */
public class Obstacle {
    Bitmap image;
    int obstacleVelocity;
    int x, y = 0;
    int screenWidth, screenHeight;
    int score = 0;

    public Obstacle(Bitmap image, int width, int height, int obstacleVelocity) {
        this.image = image;
        this.obstacleVelocity = obstacleVelocity;
        screenWidth = width;
        screenHeight = height;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public void update() {
        if (y + image.getHeight() < screenHeight) {
            y += obstacleVelocity;
        } else {
            x = (int) (Math.random() * (screenWidth + 1));
            y = 0;
            score++;
        }
    }

    public int getScore() {
        return score;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getImageWidth() {
        return image.getWidth();
    }

    public int getImageHeight() {
        return image.getWidth();
    }
}
