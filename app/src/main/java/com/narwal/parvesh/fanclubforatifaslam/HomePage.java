package com.narwal.parvesh.fanclubforatifaslam;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by Parvesh on 15-Jul-17.
 *
 */

public class HomePage extends Activity implements View.OnClickListener {


    //v, p, w, t, f, r, s, n;

    private int[] btnArray = {R.id.btnV,R.id.btnP,R.id.btnW, R.id.btnT,R.id.btnF,R.id.btnR, R.id.btnS, R.id.btnN };

    private Button[] btn = new Button[btnArray.length]; //v, p, w, t, f, r, s, n;

    private InterstitialAd mInterstitialAd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.home);

        init_activity();
    }

    private void init_activity() {

        for(int i=0; i< btnArray.length; i++){
            btn[i] = (Button) findViewById(btnArray[i]);
            btn[i].setOnClickListener(this);
        }

        AdView adView = (AdView) findViewById(R.id.adView_menu);

        AdRequest adRequest = new   AdRequest.Builder()
                .addTestDevice("8BE8614F2298107266F01F0E41BEDE27")
                .build();

        adView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4327820221556313/6142337381");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

           /* @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }*/
        });

    }

    @Override
    public void onClick(final View view) {


        YoYo.with(Techniques.TakingOff)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        YoYo.with(Techniques.FadeIn).duration(0).playOn(view);

                        if(mInterstitialAd.isLoaded()) mInterstitialAd.show();

                        if(isNetworkAvailable()){
                            DoTask(view);
                        }

                        else{
                            Toast.makeText(HomePage.this, "Sorry! You are offline. Please switch on your mobile data or Wi-Fi.", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                }).playOn(view);


    }

    public void DoTask(View view){

        Intent intent;

        switch (view.getId()){
            case R.id.btnV:
                //open videos page
                intent = new Intent(HomePage.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btnP:
                //open photos
                intent = new Intent(HomePage.this, WebPageHandler.class);
                intent.putExtra("URL", "https://pinterest.com/search/pins/?q=atif%20aslam");
                intent.putExtra("title", "Atif Aslam - Photos");
                startActivity(intent);
                break;
            case R.id.btnN:
                //open news
                intent = new Intent(HomePage.this, WebPageHandler.class);
                intent.putExtra("URL", "https://news.google.com/news/u/0/search/section/q/atif%20aslam");
                intent.putExtra("title", "Atif Aslam - News");
                startActivity(intent);
                break;
            case R.id.btnW:
                //open wiki
                intent = new Intent(HomePage.this, WebPageHandler.class);
                intent.putExtra("URL", "https://en.wikipedia.org/wiki/Atif_Aslam");
                intent.putExtra("title", "Atif Aslam - Bio");
                startActivity(intent);
                break;
            case R.id.btnT:
                //open twitter page
                intent = new Intent(HomePage.this, WebPageHandler.class);
                intent.putExtra("URL", "https://twitter.com/itsaadee");
                intent.putExtra("title", "Atif Aslam - Twitter");
                startActivity(intent);
                break;
            case R.id.btnF:
                //open facebook page
                intent = new Intent(HomePage.this, WebPageHandler.class);
                intent.putExtra("URL", "https://www.facebook.com/AtifAslamOfficialFanPage/");
                intent.putExtra("title", "Atif Aslam - Facebook");
                startActivity(intent);
                break;
            case R.id.btnR:
                //open rate us
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                break;
            case R.id.btnS:
                //open share us
                String textToShare =
                        "Best Fan Club For Atif Aslam is here: https://play.google.com/store/apps/details?id="
                        + getApplicationContext().getPackageName();
                intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, textToShare);
                intent.setType("text/plain");
                Intent.createChooser(intent, "Share via");
                startActivity(intent);
                break;
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
