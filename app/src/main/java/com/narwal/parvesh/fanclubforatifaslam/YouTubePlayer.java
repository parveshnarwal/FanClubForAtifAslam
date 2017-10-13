package com.narwal.parvesh.fanclubforatifaslam;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by Parvesh on 09-Jul-17.
 *
 */

public class YouTubePlayer extends YouTubeBaseActivity {


    private  String songID = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtubeplayer);

        Intent intent = getIntent();
        if(intent.hasExtra("youtube_video_id"))
        songID = intent.getStringExtra("youtube_video_id");


        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);

        com.google.android.youtube.player.YouTubePlayer.OnInitializedListener onInitializedListener = new com.google.android.youtube.player.YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(com.google.android.youtube.player.YouTubePlayer.Provider provider, com.google.android.youtube.player.YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(songID);
                youTubePlayer.setFullscreen(true);
            }

            @Override
            public void onInitializationFailure(com.google.android.youtube.player.YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        String API_KEY = "AIzaSyACbDBfYNrRUmy2NLyCecsuIQ_SBMJXlgg";
        youTubePlayerView.initialize(API_KEY, onInitializedListener);

    }
}
