package net.myapp.pipeline.Views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import net.myapp.pipeline.DrawThreads.GameThread;

/**
 * Created by Wanhe on 9/5/2017.
 */

public class View_DialogPause extends View
{

    private Dialog dialogContext;
    private Context context;

    public View_DialogPause(Context context, Dialog dialogContext)
    {
        super(context);

        this.context = context;
        this.dialogContext = dialogContext;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        int w = canvas.getWidth();
        int h = canvas.getHeight();


        canvas.drawColor(Color.argb(100, 0, 0, 0));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_UP)
        {
            this.dialogContext.dismiss();
            GameThread.isPaused = true;
        }

        return true;
    }
}
