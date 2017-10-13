package com.narwal.parvesh.fanclubforatifaslam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by Parvesh on 15-Jul-17.
 *
 */

public class WebPageHandler extends AppCompatActivity {

    private  String webURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();

        if(intent.hasExtra("URL")){
            webURL = intent.getStringExtra("URL");
        }


        setContentView(R.layout.webviewpage);

        if(intent.hasExtra("title")){
            setTitle(intent.getStringExtra("title"));
        }

        final WebView webView = (WebView) findViewById(R.id.wvWebPage);
        final ImageView loading = (ImageView) findViewById(R.id.ivLoading);

        YoYo.with(Techniques.Pulse)
                .duration(2000)
                .repeat(15)
                .playOn(loading);

        // zooming out page
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        //below code is copied from Stack Exchange

        webView.getSettings().setJavaScriptEnabled(true);

        final Activity activity = this;

        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description,
                            String failingUrl) {
                Toast.makeText(activity, description,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loading.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }
        });


        try {
            if(!webURL.isEmpty())
            webView.loadUrl(webURL);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

