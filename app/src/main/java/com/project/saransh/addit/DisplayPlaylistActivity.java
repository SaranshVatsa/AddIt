package com.project.saransh.addit;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DisplayPlaylistActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_playlist);

        final Intent intent = getIntent();

        try {
            if (intent.getStringExtra("Parent").equalsIgnoreCase("main")) {
                final String response= intent.getStringExtra("Response");
                new PlaylistRequester() {

                    protected void onPostExecute(Object[] result) {
                        Intent intent1=new Intent(getApplicationContext(), ListPlaylistActivity.class);
                        intent1.putExtra("AccessData", response);
                        intent1.putExtra("Data", result[0].toString());
                        intent1.putExtra("UserData", result[1].toString());
                        startActivity(intent1);
                    }

                }.execute("https://api.spotify.com/v1/me/playlists", response);
            } else {
                //Toast.makeText(getApplicationContext(), "Auth Done", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            //Error

        }
    }
}
