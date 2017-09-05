package net.myapp.pipeline.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import net.myapp.pipeline.Activities.Activity_Field;
import net.myapp.pipeline.Activities.Activity_Levels;
import net.myapp.pipeline.MyButton;
import net.myapp.pipeline.R;
import net.myapp.pipeline.Sprite;

/**
 * Created by Wanhe on 8/29/2017.
 */

public class View_Levels extends View
{
    private Bitmap bg, level_number_sprite, level_fild, level_title;
    private Context context;

    private MyButton bt[];
    private Paint fPaint;

    public View_Levels(Context context) {
        super(context);
        this.bg = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_bg);
        this.level_number_sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.level_nuber);
        this.level_fild = BitmapFactory.decodeResource(context.getResources(), R.drawable.level_fild);
        this.level_title = BitmapFactory.decodeResource(context.getResources(), R.drawable.level_title);

        this.context = context;

        this.bt = new MyButton[45];

        this.fPaint = new Paint();
        this.fPaint.setAlpha(150);
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        int w = canvas.getWidth(); //  Размер экрана //
        int h = canvas.getHeight();

        Bitmap out = Bitmap.createScaledBitmap(this.bg, w, h, true);
        canvas.drawBitmap(out, 0, 0, null); // сама отрисовка х у

        out = Bitmap.createScaledBitmap(this.level_title, (int)(w * 0.4), (int)(h * 0.15), true);
        canvas.drawBitmap(out, (int)(w * 0.35), (int)(h * 0.05), null);

        out = Bitmap.createScaledBitmap(this.level_fild, (int)(w * 0.9), (int)(h * 0.7), true);
        canvas.drawBitmap(out, (int)(w * 0.05), (int)(h * 0.25), this.fPaint);

        Sprite img_sprite = new Sprite(this.level_number_sprite, (int)(w * 0.9), (int)(h * 0.7), 5, 9);

        int count = 0;

        for(float y = 0.25f, i = 0; i < 5; y += 0.14f, i++)
        {
            for(float x = 0.05f, j = 0; j < 9; x += 0.1f, count++, j++)
            {
                this.bt[count] = new MyButton((int)(w * 0.1f), (int)(h * 0.14f), (int)(w * x), (int)(h * y), img_sprite.getImg((int)i, (int)j));
                canvas.drawBitmap(bt[count].img, bt[count].x, bt[count].y, null);
            }
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();

        if(event.getAction() == MotionEvent.ACTION_UP)
        {
            for(int i = 0; i < bt.length; i++)
            {
                if(bt[i].isClicked(x, y))
                {
                    ((Activity) this.context).finish(); // завершение текущей активити
                    ((Activity) this.context).overridePendingTransition(0, 0); // убать анимуцию переключения между активити
                    Intent intent = new Intent(this.context, Activity_Field.class); // инициализация активити
                    intent.putExtra("level", i+1); // передача информации в активити
                    this.context.startActivity(intent); // старт активити
                }
            }
        }


        return true;
    }
}
