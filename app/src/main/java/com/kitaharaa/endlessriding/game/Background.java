package com.kitaharaa.endlessriding.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {
    Bitmap backgroundImage;
    int width;

    public Background(Bitmap image,int width) {
        backgroundImage = image;
        this.width = width;
    }

    public void draw(Canvas canvas) {
        var countX = (-backgroundImage.getWidth() + width)/2;
        canvas.drawBitmap(backgroundImage, countX, 0, null);
    }
}
