package com.narwal.parvesh.fanclubforatifaslam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by Parvesh on 14-Jul-17.
 *
 */

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final int SPLASH_TIME_OUT = 3000;

        // full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ImageView splashImage = (ImageView) findViewById(R.id.ivSplash);

        YoYo.with(Techniques.FadeIn)
                .duration(2500)
                .repeat(1)
                .playOn(splashImage);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(SPLASH_TIME_OUT);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent openStartingPoint = new Intent(Splash.this, HomePage.class);
                    startActivity(openStartingPoint);
                }
            }

        };

        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
