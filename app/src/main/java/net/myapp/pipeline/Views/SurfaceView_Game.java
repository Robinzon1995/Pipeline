package net.myapp.pipeline.Views;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import net.myapp.pipeline.Dialogs.DialogPause;
import net.myapp.pipeline.DrawThreads.GameThread;

/**
 * Created by Wanhe on 9/5/2017.
 */

public class SurfaceView_Game extends SurfaceView implements SurfaceHolder.Callback
{
    private Context context;
    private GameThread thread;
    private int level;

    public SurfaceView_Game(Context context, int level)
    {
        super(context);

        getHolder().addCallback(this);
        getHolder().setFormat(PixelFormat.RGBA_8888);

        this.context = context;
        this.level = level;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        this.thread = new GameThread(holder, this.context, this.level);
        this.thread.setRunning(true);
        this.thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        thread.setRunning(false);

        while(retry)
        {
            try
            {
                thread.join();
                retry = false;
            }
            catch (InterruptedException e) {}
        }
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
                    if(this.thread.bt[i].isClicked(x, y))
                    {
                        switch(i)
                        {
                            case 0://pause
                                DialogPause dialogPause = new DialogPause(this.context);
                                this.thread.isPaused = false;
                                dialogPause.show();
                                break;
                            case 1: // End
                                this.thread.ventel_count = 0;
                                break;
                        }
                    }
                }
            }

            return true;
        }
    }
}
