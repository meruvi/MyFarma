package com.zeus;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class SplashScreenActivity extends AppCompatActivity {

    // Set the duration of the splash screen
    private static final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        Util.copiarBD(getApplicationContext());
//
//        MainV7 mainV7=new MainV7();
//        mainV7.init(getApplicationContext());
//
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                Intent mainIntent = new Intent().setClass(SplashScreenActivity.this, LoginActivity.class);
//                startActivity(mainIntent);
//                finish();
//            }
//        };
//
//        // Simulate a long loading process on application startup.
//        Timer timer = new Timer();
//        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
}
