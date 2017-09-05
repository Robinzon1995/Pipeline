package net.myapp.pipeline.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.support.annotation.IntegerRes;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import net.myapp.pipeline.MyButton;
import net.myapp.pipeline.R;
import net.myapp.pipeline.Sprite;

/**
 * Created by Wanhe on 8/31/2017.
 */

public class View_Field extends View
{
    private Bitmap bg, game_field, game_time, game_muves, game_level,bt_pause,img_sprite_pipeline,level_number_sprite, img_ventel_sprite;
    private Context context;
    private int level;
    private MyButton bt[];


    public View_Field(Context context, int level)
    {
        super(context);
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
    }

    private int ventel_count = 7;
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        int w = canvas.getWidth(); //  Размер экрана //
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

        out = Bitmap.createScaledBitmap(this.game_muves, (int)(w * 0.09), (int)(h * 0.09), true);
        canvas.drawBitmap(out, (int)(w * 0.6), (int)(h * 0.05), null);

        out = Bitmap.createScaledBitmap(this.game_field, (int)(w * 0.9), (int)(h * 0.7), true);
        canvas.drawBitmap(out, (int)(w * 0.05), (int)(h * 0.16), null);

        this.bt[0] = new MyButton((int)(w * 0.09), (int)(h * 0.1), (int)(w * 0.9), (int)(h * 0.05), this.bt_pause);
        out = Bitmap.createScaledBitmap(bt[0].img, bt[0].w, bt[0].h, true);
        canvas.drawBitmap(out,  bt[0].x, bt[0].y, null);

        Sprite img_sprite = new Sprite(img_sprite_pipeline, (int)(w * 0.8 ), (int)(h * 0.14), 1, 7);

        /// animal
        Sprite sprite_ventel = new Sprite(this.img_ventel_sprite, (int)(w * 0.80), (int)(h * 0.14), 1, 8);

        this.bt[1] = new MyButton(img_sprite.w, img_sprite.h, (int)(w * 0.83),(int)(h* 0.85), img_sprite.getImg(0, 5));
        canvas.drawBitmap(bt[1].img, bt[1].x, bt[1].y, null);

        if(ventel_count != 7)
        {
            canvas.drawBitmap(sprite_ventel.getImg(0, ventel_count), (int)(bt[1].x * 1.02), bt[1].y, null);
            ventel_count++;
            postInvalidateDelayed(33);
        }
        else
        {
            canvas.drawBitmap(sprite_ventel.getImg(0, 0),  (int)(bt[1].x * 1.02), bt[1].y, null);
        }

        out = Bitmap.createScaledBitmap(img_sprite.getImg(0,5), img_sprite.w, img_sprite.h, true);
        canvas.drawBitmap(out, (int)(w * 0.05), (int)(h * 0.85), null);


    }

    public boolean onTouchEvent(MotionEvent event)
    {
        {
            float x = event.getX();// точка клика
            float y = event.getY();

            if(event.getAction() == MotionEvent.ACTION_UP)
            {
                for(int i = 0; i <= 1; i++)
                {
                    if(this.bt[i].isClicked(x, y))
                    {
                        switch(i)
                        {
                            case 0://pause
                                Toast.makeText(context, "Нажал пауза", Toast.LENGTH_LONG).show();
                                break;
                            case 1: // End
                                ventel_count = 0;
                                postInvalidateDelayed(33);
                                break;
                            case 3:///

                                break;
                            case 4:

                                break;
                            case 5:

                                break;
                            case 6:

                                break;
                            case 7:

                                break;
                            case 8:

                                break;
                            case 9:

                                break;
                        }
                    }
                }
            }

            return true;
        }
    }
}
