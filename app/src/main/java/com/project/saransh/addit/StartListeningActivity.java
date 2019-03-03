package com.project.saransh.addit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class StartListeningActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_listening);

        TextView textView=(TextView)findViewById(R.id.textView3);

        Intent intent=getIntent();
        String playlistID=intent.getStringExtra("PlaylistID");
        textView.setText(playlistID);
        try {

            new TrackSearcher() {

                protected void onPostExecute(Object[] result) {
                    String response = result[0].toString();
                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//                        String trackID=jsonObject.getJSONObject("tracks").getJSONArray("items").getJSONObject(0).getString("uri");
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }

            }.execute(intent.getStringExtra("AccessData"), playlistID);

        }catch (Exception e){
            //Error
        }
    }
}
