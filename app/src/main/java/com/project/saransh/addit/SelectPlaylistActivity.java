package com.project.saransh.addit;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.spotify.android.appremote.api.ConnectionParams;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


public class SelectPlaylistActivity extends AppCompatActivity {

    private static final String CLIENT_ID = "#YOUR_SPOTIFY_CLIENT_ID#";   //ADD YOUR ID HERE
    private static final String CLIENT_SECRET = "#YOUR_SPOTIFY_CLIENT_ID#"; //ADD YOUR SECRET KEY HERE
    private static final String REDIRECT_URI = "http://com.project.saransh/callback"; 


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_playlist);

        TextView textView=(TextView)findViewById(R.id.textView);

        Intent intent = getIntent();

        try {
            if (intent.getStringExtra("Parent").equalsIgnoreCase("main")) {
                String authURL = "https://accounts.spotify.com:443/authorize?client_id="+CLIENT_ID+"&response_type=code&redirect_uri=http://com.project.saransh/callback&scope=user-read-private%20user-read-email%20playlist-read-private%20playlist-modify-private%20playlist-modify-public%20user-read-private%20user-read-email%20user-read-birthdate";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(authURL));
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "Auth Done", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Auth Done", Toast.LENGTH_SHORT).show();
            String action = intent.getAction();
            Uri data = intent.getData();
            //Toast.makeText(getApplicationContext(), data.getQueryParameter("code").toString(), Toast.LENGTH_LONG).show();

            new RequestMaker() {

                protected void onPostExecute(Object[] result) {

                    String response = result[0].toString();
                    Intent intent1 = new Intent(getApplicationContext(), DisplayPlaylistActivity.class);
                    intent1.putExtra("Parent", "main");
                    intent1.putExtra("Response", response);
                    startActivity(intent1);
//                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                }

            }.execute("https://accounts.spotify.com/api/token",data.getQueryParameter("code").toString(), CLIENT_ID, CLIENT_SECRET);

            textView.setText(data.getQueryParameter("code").toString());


//        Toast.makeText(getApplicationContext(), intent.toString(), Toast.LENGTH_SHORT).show();


        }

    }
}
