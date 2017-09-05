package net.myapp.pipeline.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.view.WindowManager;

import net.myapp.pipeline.Views.View_DialogPause;
import net.myapp.pipeline.Views.View_Menu;

/**
 * Created by Wanhe on 9/5/2017.
 */

public class DialogPause extends Dialog
{

    private Context context;

    public DialogPause(@NonNull Context context)
    {
        super(context);

        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        setContentView(new View_DialogPause(context, this));

        setCancelable(false);
    }
}
