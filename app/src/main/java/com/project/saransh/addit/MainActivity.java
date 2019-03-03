package com.project.saransh.addit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startListening(View view){
        //Start Activity
        Intent intent = new Intent(this, SelectPlaylistActivity.class);
        intent.putExtra("Parent", "main");
        startActivity(intent);
    }
}
