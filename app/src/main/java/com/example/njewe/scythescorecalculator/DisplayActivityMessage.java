package com.example.njewe.scythescorecalculator;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.GridLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import java.util.ArrayList;

public class DisplayActivityMessage extends Activity {

    private final String  players[] = {"Player", "Popularity", "Stars", "Territory", "Resources", "Structure", "Money", "Total"};
    private ArrayList<Player> player;


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
        for(int i = 0; i < numOfPlayers; i++){
            player.add(new Player(i + 1));
        }

        GridLayout grid = new GridLayout(DisplayActivityMessage.this);
         grid.setLayoutParams(new LayoutParams
                 (LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        grid.setColumnCount(numOfCol);

        TextView[] text = new TextView[numOfRow * numOfCol];

        for(int col = 0; col < numOfCol; col++){
            for(int row = 0; row < numOfRow; row++){
                switch (col){

                    case 0:
                        text[col+row] = new TextView(DisplayActivityMessage.this);
                        text[col+row].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        text[col+row].setText(players[row]);
                        text[col+row].setPadding(50, 25, 10, 25);
                        grid.addView(text[col+row]);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                }
            }
        }


        setContentView(grid);

        }

}
