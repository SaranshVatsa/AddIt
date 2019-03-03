package com.project.saransh.addit;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestMaker extends AsyncTask<String, Integer, Object[]> {

    @Override
    protected Object[] doInBackground(String... params) {
        // TODO Auto-generated method stub

        StringBuffer jsonString = new StringBuffer();
        try{
            HttpURLConnection connection = null;
            URL object = new URL(params[0].toString());
            connection = (HttpURLConnection) object.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestProperty("Accept","application/json");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//            connection.setRequestProperty("Authorization", "Bearer "+params[1].toString());
            connection.connect();
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
//            writer.write("{\"grant_type\": \"authorization_code\", \"client_id\": \"" +params[2]+ "\", \"client_secret\": \""+params[3]+"\", \"code\": \""+params[1]+ "\", \"redirect_uri\": \"http://com.project.saransh/callback\"}");
            writer.write("grant_type=authorization_code&client_id="+params[2]+"&client_secret="+params[3]+"&code="+params[1]+"&redirect_uri=http://com.project.saransh/callback");
            writer.close();

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
//        jsonString.append("FAIL");
//        jsonString.append(params[0]);
//        jsonString.append(params[1]);
        Object obj[]={jsonString.toString()};
        return obj;

    }
}