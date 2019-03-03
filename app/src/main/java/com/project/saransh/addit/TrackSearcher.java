package com.project.saransh.addit;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class TrackSearcher extends AsyncTask<String, Integer, Object[]> {

    @Override
    protected Object[] doInBackground(String... params) {
        // TODO Auto-generated method stub

        StringBuffer jsonString = new StringBuffer();
        try{
            HttpURLConnection connection = null;
            URL object = new URL("https://api.spotify.com/v1/search?q=artist:Eminem%20track:Lose+Yourself&type=track&limit=1");

            JSONObject jsonObject=new JSONObject(params[0]);
            connection = (HttpURLConnection) object.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept","application/json");
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("Authorization", "Bearer "+jsonObject.get("access_token").toString());
            connection.connect();


            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                while ((line = br.readLine()) != null) {
                    jsonString.append(line);
                }
                br.close();
                connection.disconnect();
            }

        }catch (Exception e){
            //Error
            jsonString.append("FAIL");
            jsonString.append(e.toString());
        }

        String playlistID=params[1];
        try {
            JSONObject jsonObject=new JSONObject(params[0]);
            JSONObject jsonObject2 = new JSONObject(jsonString.toString());
            String trackID = jsonObject2.getJSONObject("tracks").getJSONArray("items").getJSONObject(0).getString("uri");
            String postURL = "https://api.spotify.com/v1/playlists/" + playlistID + "/tracks?uris=" +trackID;


            HttpURLConnection connection2 = null;
            URL object = new URL(postURL);
            connection2 = (HttpURLConnection) object.openConnection();
            connection2.setRequestMethod("POST");
            connection2.setDoInput(true);
            connection2.setInstanceFollowRedirects(false);
            connection2.setRequestProperty("Accept","application/json");
            connection2.setRequestProperty("Authorization", "Bearer "+jsonObject.get("access_token").toString());
            connection2.connect();

            if (connection2.getResponseCode() == 201) {
                connection2.disconnect();
            }
        }catch (Exception e){
            //Error
        }
        Object obj[]={"Track Added"};
        return obj;

    }
}
