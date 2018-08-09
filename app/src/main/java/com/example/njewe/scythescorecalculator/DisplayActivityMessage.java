package com.example.njewe.scythescorecalculator;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.njewe.scythescorecalculator.Player.FACTORY_TOGGLE;

public class DisplayActivityMessage extends Activity {

    private final String  category[] = {"Player", "Popularity", "Stars", "Territory", "Factory", "Resources \n(pairs)", "Structure", "Money", "Total"};
    private ArrayList<Player> player;
    private ArrayList<Integer> factoryIds;
    private int factoryOwnerID;
    private TextView[] text;
    private GridLayout grid;
    int numOfPlayers;
    int numOfCol;
    private int numOfRow;
    private static final int MAX_LENGTH = 2;
    private static final int FACTORY_MAX_LENGTH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);


        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        numOfPlayers = Integer.parseInt(message);
        numOfCol = numOfPlayers + 1;
        numOfRow = category.length;

        createPlayerObjects();

        grid = this.findViewById(R.id.GridLayout1);
        grid.setColumnCount(numOfCol);
        createScoreGrid();
    }

    private void createScoreGrid() {
        text = new TextView[numOfRow * numOfCol];
        factoryIds = new ArrayList<Integer>();
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
                }else if(row == 0) {
                    text[count] = new TextView(DisplayActivityMessage.this);
                    text[count].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                    text[count].setTypeface(Typeface.DEFAULT_BOLD);
                    text[count].setText(player.get(col-1).values.get(row).toString());
                }else if (row == 4) {
                    text[count] = new EditText(DisplayActivityMessage.this);
                    text[count].setOnFocusChangeListener(textFocus);
                    text[count].setInputType(InputType.TYPE_CLASS_NUMBER);
                    text[count].setHintTextColor(Color.RED);
                    text[count].setFilters(new InputFilter[]{ new InputFilterMinMax("0" , "1"), new InputFilter.LengthFilter(FACTORY_MAX_LENGTH)});
                    text[count].setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
                    text[count].setHint(player.get(col-1).values.get(row).toString());
                    text[count].setId(count);
                    factoryIds.add(count);
                    text[count].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                    //text[count].addTextChangedListener(getTextWatcher((EditText)text[count]));

                }else{
                    text[count] = new EditText(DisplayActivityMessage.this);
                    text[count].setOnFocusChangeListener(textFocus);
                    text[count].setId(count);
                    text[count].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                    text[count].setInputType(InputType.TYPE_CLASS_NUMBER);
                    text[count].setHintTextColor(Color.RED);
                    text[count].setFilters(new InputFilter[] {new InputFilter.LengthFilter(MAX_LENGTH)});
                    text[count].setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
                    text[count].setHint(player.get(col-1).values.get(row).toString());
                }
                text[count].setPadding(50, 25, 10, 25);
                grid.addView(text[count]);
            }
        }
    }

    private void createPlayerObjects() {
        player = new ArrayList<Player>();
        for (int i = 0; i < numOfPlayers; i++) {
            player.add(new Player(i + 1));
        }
    }
    private View.OnFocusChangeListener textFocus = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean hasfocus) {
            int id = ((EditText) view).getId();
            int playerNo = calculatePlayer(id);
            int category = calculateCategory(id);
            int index = (text.length - numOfCol) + (playerNo + 1);

            if(!hasfocus) {
                if (!((EditText) view).getText().toString().isEmpty()) {

                    int value = Integer.parseInt(((EditText) view).getText().toString());
                    player.get(playerNo).setCategory(category, value);

                    if (FACTORY_TOGGLE == 1 && value == 1 && category == 4) {
                        factoryOwnerID = index;
                    }

                    player.get(playerNo).calculateScore();
                    text[index].setText(Integer.toString(player.get(playerNo).getTotal()));
                    releaseFactoryScore(category, value, index);
                }
            }else{
                assignFactoryScore(category, index);
            }
        }
    };

    private void assignFactoryScore(int category, int index) {
        if(category == 4) {
            if (FACTORY_TOGGLE == 1) {
                for(Integer i: factoryIds) {
                    if(i != factoryOwnerID)
                        text[i].setFilters(new InputFilter[]{new InputFilterMinMax("0", "0"), new InputFilter.LengthFilter(FACTORY_MAX_LENGTH)});
                }
            }
        }
    }

    private void releaseFactoryScore(int category, int value, int index) {
        if(category == 4 && value == 0 && factoryOwnerID == index){
            FACTORY_TOGGLE = 0;
            factoryOwnerID = 0;
            for(Integer i: factoryIds) {
                text[i].setFilters(new InputFilter[]{new InputFilterMinMax("0", "1"), new InputFilter.LengthFilter(FACTORY_MAX_LENGTH)});
            }
        }
    }

    private TextWatcher getTextWatcher(final EditText editText) {
        return new TextWatcher() {
            int id = ((EditText) editText).getId();
            int playerNo = calculatePlayer(id);
            int category = calculateCategory(id);

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                       if(FACTORY_TOGGLE == 1){
                           editText.setFilters(new InputFilter[]{ new InputFilterMinMax("0" , "0")});
                       }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                player.get(playerNo).setCategory(category, Integer.parseInt(((EditText) editText).getText().toString()));
                player.get(playerNo).calculateScore();
            }
        };
    }

    private int calculateCategory(int index) {
        return (int)(index / numOfCol);
    }

    private int calculatePlayer(int index) {
        return (index % numOfCol)-1;
    }
}
