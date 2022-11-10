package com.kitaharaa.endlessriding.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Car {
    private final Bitmap image;
    public int x, y;
    private final int imageWidth;
    private final int imageHeight;

      int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
      int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public Car(Bitmap image) {
        this.image = image;

        imageWidth = image.getWidth();
        imageHeight = image.getHeight();

        x = (screenWidth - image.getWidth())/2;
        y = screenHeight  - image.getHeight();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public void update() {

    }

    public void setX(int x) {
        this.x = x - imageWidth/2;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }
}
