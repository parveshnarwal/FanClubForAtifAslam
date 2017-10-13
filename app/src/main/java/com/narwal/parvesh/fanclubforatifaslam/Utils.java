package com.narwal.parvesh.fanclubforatifaslam;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * Created by Parvesh on 09-Jul-17.
 */

class Utils {


    static YouTubeSong[] FetchSongsFromJSON(String JSONData) throws JSONException {

        YouTubeSong[] youTubeSongs;

        JSONObject obj = new JSONObject(JSONData);

        JSONArray jsonArray = obj.getJSONArray("items");

        youTubeSongs = new YouTubeSong[jsonArray.length()];

        for(int i = 0; i < jsonArray.length(); i++){
            YouTubeSong youTubeSong = new YouTubeSong();

            String title = jsonArray.getJSONObject(i).getJSONObject("snippet").getString("title");
            String description = jsonArray.getJSONObject(i).getJSONObject("snippet").getString("description");
            String songID = jsonArray.getJSONObject(i).getJSONObject("snippet").getJSONObject("resourceId").getString("videoId");

            youTubeSong.setSongID(songID);
            youTubeSong.setTitle(title);
            youTubeSong.setDescription(description);

            youTubeSongs[i] = youTubeSong;

        }

        return youTubeSongs;
    }
}
