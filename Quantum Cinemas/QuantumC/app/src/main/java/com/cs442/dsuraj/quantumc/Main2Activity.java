package com.cs442.dsuraj.quantumc;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Animation animation;
        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation);
        ImageView q = (ImageView) findViewById(R.id.imageView);
        q.startAnimation(animation);
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Main2Activity.this, SeatSelection.class);
                startActivity(intent);
                finishscreen();
            }
        };
        Timer t = new Timer();
        t.schedule(task, 4000);
    }

    private void finishscreen() {
        this.finish();
    }
    }

