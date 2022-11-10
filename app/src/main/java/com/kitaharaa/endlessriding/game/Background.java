package com.kitaharaa.endlessriding.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/* Set background to game*/
public class Background {
    Bitmap backgroundImage;
    int width;

    /* Constructor */
    public Background(Bitmap image,int width) {
        backgroundImage = image;
        this.width = width;
    }

    /* Draw elements */
    public void draw(Canvas canvas) {
        var countX = (-backgroundImage.getWidth() + width)/2;
        canvas.drawBitmap(backgroundImage, countX, 0, null);
    }
}
