package com.project.saransh.addit;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListPlaylistActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_playlist);

        final ListView listView = (ListView) findViewById(R.id.listView1);

        final Intent intent = getIntent();
        try {
            JSONObject response2 = new JSONObject(intent.getStringExtra("Data"));
            JSONObject responseUser = new JSONObject(intent.getStringExtra("UserData"));

            String userID= responseUser.getString("id");
            ArrayList<String> stringArray = new ArrayList<String>();
            final ArrayList<String> idArray = new ArrayList<String>();
            if (Build.VERSION.SDK_INT >= 19) {
                //Toast.makeText(getApplicationContext(), response2.getJSONArray("items").toString(), Toast.LENGTH_LONG).show();
                JSONArray jsonArray = response2.getJSONArray("items");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if(jsonObject.getJSONObject("owner").getString("id").equalsIgnoreCase(userID)) {
                        idArray.add(jsonObject.getString("id").toString());
                        stringArray.add(jsonObject.getString("name").toString());
                    }
                }

                try {
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                            getApplicationContext(),
                            android.R.layout.simple_list_item_1,
                            stringArray);

                    listView.setAdapter(arrayAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent1=new Intent(getApplicationContext(), StartListeningActivity.class);
                            intent1.putExtra("PlaylistID", idArray.get(position).toString());
                            intent1.putExtra("AccessData", intent.getStringExtra("AccessData"));
                            startActivity(intent1);
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "IN HERE: "+ e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "HERE: "+e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
