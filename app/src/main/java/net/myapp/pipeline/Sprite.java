package net.myapp.pipeline;

import android.graphics.Bitmap;

/**
 * Created by Wanhe on 8/29/2017.
 */

public class Sprite
{
    private Bitmap sprite;

    public int w, h, rows, cols;

    public Sprite(Bitmap sprite, int w, int h, int rows, int cols)
    {
        this.sprite = Bitmap.createScaledBitmap(sprite, w, h, true);
        this.w = w / cols;
        this.h = h / rows;
        this.rows = rows;
        this.cols = cols;
    }

    public Bitmap getImg(int row, int col)
    {
        return Bitmap.createBitmap(sprite, w * col, h * row, w, h);
    }
}
