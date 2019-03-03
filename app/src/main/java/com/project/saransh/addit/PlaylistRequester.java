package com.project.saransh.addit;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PlaylistRequester extends AsyncTask<String, Integer, Object[]> {

    @Override
    protected Object[] doInBackground(String... params) {
        // TODO Auto-generated method stub


        StringBuffer jsonString = new StringBuffer();
        StringBuffer jsonStringUser = new StringBuffer();
        try{
            HttpURLConnection connection = null;
            URL object = new URL(params[0].toString());

            JSONObject jsonObject=new JSONObject(params[1]);
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
            jsonString.append(params[0]);
            jsonString.append(params[1]);
        }

        try{
            HttpURLConnection connection2 = null;
            URL object2 = new URL("https://api.spotify.com/v1/me");

            JSONObject jsonObject2=new JSONObject(params[1]);
            connection2 = (HttpURLConnection) object2.openConnection();
            connection2.setRequestMethod("GET");
            connection2.setRequestProperty("Accept","application/json");
            connection2.setRequestProperty("Content-Type","application/json");
            connection2.setRequestProperty("Authorization", "Bearer "+jsonObject2.get("access_token").toString());
            connection2.connect();


            if (connection2.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br2 = new BufferedReader(new InputStreamReader(connection2.getInputStream()));

                String line2;
                while ((line2 = br2.readLine()) != null) {
                    jsonStringUser.append(line2);
                }
                br2.close();
                connection2.disconnect();
            }

        }catch (Exception e){
            //Error
            jsonStringUser.append("FAIL");
            jsonStringUser.append(params[1]);
        }
//        jsonString.append("FAIL");
//        jsonString.append(params[0]);
//        jsonString.append(params[1]);
        Object obj[]={jsonString.toString(), jsonStringUser.toString()};
        return obj;

    }
}