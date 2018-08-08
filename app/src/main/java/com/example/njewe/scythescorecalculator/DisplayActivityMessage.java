package com.example.njewe.scythescorecalculator;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Activity;
import android.widget.GridLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayActivityMessage extends Activity {

    private final String  category[] = {"Player", "Popularity", "Stars", "Territory", "Resources", "Structure", "Money", "Total"};
    private ArrayList<Player> player;
    private static final String STATE_COUNTER = "counter";
    private int mCounter;

    int numOfCol;
    private final int numOfRow = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        int numOfPlayers = Integer.parseInt(message);
        numOfCol = numOfPlayers + 1;

        player = new ArrayList<Player>();
        for (int i = 0; i < numOfPlayers; i++) {
            player.add(new Player(i + 1));
        }

        GridLayout grid = new GridLayout(DisplayActivityMessage.this);
        grid.setLayoutParams(new LayoutParams
                (LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        grid.setColumnCount(numOfCol);

        TextView[] text = new TextView[numOfRow * numOfCol];
        int count = 0;
        for (int row = 0; row < numOfRow; row++) {
            for (int col = 0; col < numOfCol; col++, count++) {

                        text[count] = new TextView(DisplayActivityMessage.this);
                        text[count].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        if (col == 0) {
                            text[count].setText(category[row]);
                        } else {
                            text[count].setText(player.get(col-1).values.get(row).toString());
                        }
                        text[count].setPadding(50, 25, 10, 25);
                        grid.addView(text[count]);
            }
        }
        setContentView(grid);
    }
}
