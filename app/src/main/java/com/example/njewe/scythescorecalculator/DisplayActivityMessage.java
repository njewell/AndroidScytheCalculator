package com.example.njewe.scythescorecalculator;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayActivityMessage extends Activity {

    private final String  category[] = {"Player", "Popularity", "Stars", "Territory \n(Factory inc)", "Resources \n(pairs)", "Structure", "Money", "Total"};
    private ArrayList<Player> player;
    private TextView[] text;
    private GridLayout grid;
    int numOfPlayers;
    int numOfCol;
    private final int numOfRow = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        numOfPlayers = Integer.parseInt(message);
        numOfCol = numOfPlayers + 1;

        createPlayerObjects();


        grid = new GridLayout(DisplayActivityMessage.this);
        grid.setLayoutParams(new LayoutParams
                (LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        grid.setColumnCount(numOfCol);
        createScoreGrid();

    }

    private void createScoreGrid() {
        text = new TextView[numOfRow * numOfCol];
        int count = 0;
        for (int row = 0; row < numOfRow; row++) {
            for (int col = 0; col < numOfCol; col++, count++) {
                if (col == 0) {
                    text[count] = new TextView(DisplayActivityMessage.this);
                    text[count].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                    text[count].setTypeface(Typeface.DEFAULT_BOLD);
                    text[count].setText(category[row]);
                } else if(row == (numOfRow - 1)) {
                    text[count] = new TextView(DisplayActivityMessage.this);
                    text[count].setOnFocusChangeListener(textFocus);
                    text[count].setId(count);
                    text[count].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                    text[count].setTypeface(Typeface.DEFAULT_BOLD);
                    text[count].setInputType(InputType.TYPE_CLASS_NUMBER);
                    text[count].setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
                    text[count].setHint(player.get(col-1).values.get(row).toString());
                }else{
                    text[count] = new EditText(DisplayActivityMessage.this);
                    text[count].setOnFocusChangeListener(textFocus);
                    text[count].setId(count);
                    text[count].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                    text[count].setInputType(InputType.TYPE_CLASS_NUMBER);
                    text[count].setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
                    text[count].setHint(player.get(col-1).values.get(row).toString());
                }
                text[count].setPadding(50, 25, 10, 25);
                grid.addView(text[count]);
            }
        }
        setContentView(grid);
    }

    private void createPlayerObjects() {
        player = new ArrayList<Player>();
        for (int i = 0; i < numOfPlayers; i++) {
            player.add(new Player(i + 1));
        }
    }
    private View.OnFocusChangeListener textFocus = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            if(!b){
                if(!((EditText)view).getText().toString().isEmpty()) {
                    int value = Integer.parseInt(((EditText) view).getText().toString());
                    int id = ((EditText) view).getId();
                    int playerNo = calculatePlayer(id);
                    int category = calculateCategory(id);
                    player.get(playerNo).setCategory(category, value);
                    player.get(playerNo).calculateScore();
                    text[(text.length - numOfCol) + (playerNo + 1)].setText(Integer.toString(player.get(playerNo).getTotal()));
                }

            }
        }
    };

    private int calculateCategory(int index) {
        return (int)(index / numOfCol);
    }

    private int calculatePlayer(int index) {
        return (index % numOfCol)-1;
    }
}
