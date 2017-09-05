package net.myapp.pipeline;

import android.graphics.Bitmap;

/**
 * Created by Wanhe on 8/27/2017.
 */

public class MyButton
{
    public int x, y, w, h;
    public Bitmap img;

    public MyButton(int w, int h, int x, int y, Bitmap img)
    {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;

        this.img = img;
    }

    public boolean isClicked(float x, float y)
    {
        if(x >= this.x && x <= this.x + this.w && y >= this.y && y <= this.y + this.h)
        {
            return true;
        }

        return false;
    }
}
