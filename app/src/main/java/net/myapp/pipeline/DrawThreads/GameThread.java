package net.myapp.pipeline.DrawThreads;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;

import net.myapp.pipeline.MyButton;
import net.myapp.pipeline.R;
import net.myapp.pipeline.Sprite;

/**
 * Created by Wanhe on 9/5/2017.
 */

public class GameThread extends Thread
{
    private final int REDRAW_TIME = 33;
    private final SurfaceHolder surfaceHolder;
    private boolean isRunning;
    private long prevRedrawTime;
    private Paint paint;
    public static boolean isPaused = true;

    private Bitmap bg, game_field, game_time, game_muves, game_level,bt_pause,img_sprite_pipeline,level_number_sprite, img_ventel_sprite;
    private Context context;
    private int level;
    public MyButton bt[];

    public int ventel_count = 7;

    private int w, h;

    public GameThread(SurfaceHolder holder, Context context, int level)
    {
        surfaceHolder = holder;
        isRunning = false;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        this.level = level;
        this.context = context;
        this.bt = new MyButton[41];
        this.bg = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_bg);
        this.game_field = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_fild);
        this.game_time = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_time_2);
        this.game_muves = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_muves_2);
        this.game_level = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_level);
        this.bt_pause = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_pause);
        this.img_sprite_pipeline = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_sprite_controls);
        this.level_number_sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.level_nuber);
        this.img_ventel_sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_ventel_sprite);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        this.w = displaymetrics.widthPixels;
        this.h = displaymetrics.heightPixels;

        initButton();
    }

    private Sprite img_sprite, sprite_ventel;

    private void initButton()
    {
        this.bt[0] = new MyButton((int)(this.w * 0.09), (int)(this.h * 0.1), (int)(this.w * 0.9), (int)(this.h * 0.05), this.bt_pause);
        this.img_sprite = new Sprite(img_sprite_pipeline, (int)(this.w * 0.8 ), (int)(this.h * 0.14), 1, 7);
        this.bt[1] = new MyButton(img_sprite.w, img_sprite.h, (int)(this.w * 0.83),(int)(this.h * 0.85), img_sprite.getImg(0, 5));
        this.sprite_ventel = new Sprite(this.img_ventel_sprite, (int)(this.w * 0.80), (int)(this.h * 0.14), 1, 8);
    }

    public void setRunning(boolean running)
    {
        isRunning = running;
        prevRedrawTime = getTime();
    }

    public long getTime() {
        return System.nanoTime() / 1_000_000;
    }

    private int timer = 0;
    private long prevTimer = getTime();

    @Override
    public void run()
    {
        Canvas canvas;

        while (isRunning)
        {
            long curTime = getTime();
            long elapsedTime = curTime - prevRedrawTime;
            long elapsedTimer = curTime - prevTimer;
            prevTimer = curTime;

            if(!isPaused) { continue; }

            timer += elapsedTimer;

            if (elapsedTime < REDRAW_TIME) { continue; }

            canvas = null;

            try
            {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder)
                {
                    draw(canvas);
                }
            }
            catch (NullPointerException e) {}
            finally
            {
                if (canvas != null)
                    surfaceHolder.unlockCanvasAndPost(canvas);
            }

            prevRedrawTime = curTime;
        }
    }

    private void draw(Canvas canvas)
    {
        int w = canvas.getWidth();
        int h = canvas.getHeight();

        Paint p = new Paint();
        p.setColor(Color.rgb(164, 166, 168));
        p.setTextSize(30 * this.context.getResources().getDisplayMetrics().density);
        p.setAntiAlias(true);

        Bitmap out = Bitmap.createScaledBitmap(this.bg, w, h, true);
        canvas.drawBitmap(out, 0, 0, null); // сама отрисовка х у

        out = Bitmap.createScaledBitmap(this.game_level, (int)(w * 0.25), (int)(h * 0.1), true);
        canvas.drawBitmap(out, 0, (int)(h * 0.05), null);
        canvas.drawText(Integer.toString(this.level), (int)(w * 0.25), (int)(h * 0.14), p);

        out = Bitmap.createScaledBitmap(this.game_time, (int)(w * 0.09), (int)(h * 0.09), true);
        canvas.drawBitmap(out, (int)(w * 0.35), (int)(h * 0.05), null);

        canvas.drawText(Integer.toString(this.timer / 1000) + "s", (int)(w * 0.45), (int)(h * 0.14), p);

        out = Bitmap.createScaledBitmap(this.game_muves, (int)(w * 0.09), (int)(h * 0.09), true);
        canvas.drawBitmap(out, (int)(w * 0.6), (int)(h * 0.05), null);

        out = Bitmap.createScaledBitmap(this.game_field, (int)(w * 0.9), (int)(h * 0.7), true);
        canvas.drawBitmap(out, (int)(w * 0.05), (int)(h * 0.16), null);

        out = Bitmap.createScaledBitmap(bt[0].img, bt[0].w, bt[0].h, true);
        canvas.drawBitmap(out,  bt[0].x, bt[0].y, null);

        /// animal

        canvas.drawBitmap(bt[1].img, bt[1].x, bt[1].y, null);

        if(ventel_count != 7)
        {
            canvas.drawBitmap(sprite_ventel.getImg(0, ventel_count), (int)(bt[1].x * 1.02), bt[1].y, null);
            ventel_count++;
        }
        else
        {
            canvas.drawBitmap(sprite_ventel.getImg(0, 0),  (int)(bt[1].x * 1.02), bt[1].y, null);
        }

        out = Bitmap.createScaledBitmap(img_sprite.getImg(0,5), img_sprite.w, img_sprite.h, true);
        canvas.drawBitmap(out, (int)(w * 0.05), (int)(h * 0.85), null);
    }
}

