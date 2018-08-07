package com.example.njewe.scythescorecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.GridView;
import android.widget.TextView;

public class DisplayActivityMessage extends Activity {

    private final String  players[] = {"Player", "Popularity", "Stars", "Territory", "Resources", "Structure", "Money", "Total"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //TextView textView = findViewById(R.id.textView);
       // textView.setText(message);

        GridView gridView = (GridView)findViewById(R.id.gridview);
        gridView.setNumColumns(Integer.parseInt(message)+ 1);

        PlayerAdapter playerAdapter = new PlayerAdapter(this, players);
        gridView.setAdapter(playerAdapter);
    }

}
