package net.myapp.pipeline.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import net.myapp.pipeline.Activities.Activity_Levels;
import net.myapp.pipeline.MyButton;
import net.myapp.pipeline.R;

/**
 * Created by Wanhe on 8/22/2017.
 */

public class View_Menu extends View
{
    private Bitmap bg, img_title, img_start, img_settimgs, img_exit;
    private MyButton bt[];

    private Context context;

    public View_Menu(Context context)
    {
        super(context);

        this.bg = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_bg);
        this.img_title = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_title);
        this.img_start = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_bt_start);
        this.img_settimgs = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_bt_setting);
        this.img_exit = BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_bt_exit);

        this.bt = new MyButton[3];

        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        int w = canvas.getWidth(); //  Размер экрана //
        int h = canvas.getHeight();

        initButtons(w, h);

        Bitmap out = Bitmap.createScaledBitmap(this.bg, w, h, true);
        canvas.drawBitmap(out, 0, 0, null); // сама отрисовка х у

        out = Bitmap.createScaledBitmap(this.img_title, (int)(w * 0.5), (int)(h * 0.18), true);
        canvas.drawBitmap(out, (int)(w * 0.25), (int)(h * 0.05), null);

        for(int i = 0; i < bt.length; i++)
        {
            out = Bitmap.createScaledBitmap(bt[i].img, bt[i].w, bt[i].h, true);
            canvas.drawBitmap(out,  bt[i].x, bt[i].y, null);
        }

//        postInvalidateDelayed(33); перерисовка 33 мс
    }

    public void initButtons(int w, int h)
    {
        bt[0] = new MyButton((int)(w * 0.25), (int)(h * 0.1), (int)(w * 0.6), (int)(h * 0.4), this.img_start);
        bt[1] = new MyButton((int)(w * 0.25), (int)(h * 0.1), (int)(w * 0.6), (int)(h * 0.55), this.img_settimgs);
        bt[2] = new MyButton((int)(w * 0.25), (int)(h * 0.1), (int)(w * 0.6), (int)(h * 0.70), this.img_exit);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();// точка клика
        float y = event.getY();

        if(event.getAction() == MotionEvent.ACTION_UP)
        {
            for(int i = 0; i < bt.length; i++)
            {
                if(this.bt[i].isClicked(x, y))
                {
                    switch(i)
                    {
                        case 0:
                            ////start////
                            ((Activity) this.context).finish(); // завершение текущей активити
                            ((Activity) this.context).overridePendingTransition(0, 0); // убать анимуцию переключения между активити
                            Intent intent = new Intent(this.context, Activity_Levels.class); // инициализация активити
                            //intent.putExtra("name", "vasiya"); // передача информации в активити
                            this.context.startActivity(intent); // старт активити
                            break;
                        case 1:
                            ////settings////
                            break;
                        case 2:
                            ((Activity) this.context).finish();
                            break;
                    }
                }
            }
        }

        return true;
    }
}
