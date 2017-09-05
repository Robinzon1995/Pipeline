package net.myapp.pipeline.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import net.myapp.pipeline.Views.SurfaceView_Game;
import net.myapp.pipeline.Views.View_Field;
import net.myapp.pipeline.Views.View_Levels;

public class Activity_Field extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        Intent intent = this.getIntent();
        int level = intent.getIntExtra("level", 0);

        setContentView(new SurfaceView_Game(this, level));

    }
}
