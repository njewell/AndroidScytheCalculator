package ScytheScoreCalculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import GameScoreCalculator.R;

import java.util.ArrayList;

import static ScytheScoreCalculator.Player.FACTORY_TOGGLE;

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
    private static final int FACTORY_MAX_LENGTH = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);


        Intent intent = getIntent();
        String message = intent.getStringExtra(ScytheMainMenu.EXTRA_MESSAGE);
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
        final LayoutInflater inflater = (LayoutInflater)getSystemService (Context.LAYOUT_INFLATER_SERVICE);
        for (int row = 0; row < numOfRow; row++) {
            for (int col = 0; col < numOfCol; col++, count++) {
                if (col == 0 || row == (numOfRow - 1) || row == 0) {  //Player row, category column, total row
                    text[count] = new TextView(DisplayActivityMessage.this);
                    text[count] = (TextView)inflater.inflate(R.layout.total,null);
                    text[count].setId(count);

                    if(col == 0){
                        addImage(text[count], row);
                        text[count].setText(category[row]);
                    }else {
                        text[count].setText(player.get(col-1).values.get(row).toString());
                    }

                }else{ //Edit Text Fields
                    text[count] = new EditText(DisplayActivityMessage.this);
                    text[count] = (EditText)inflater.inflate(R.layout.edit_text, null);
                    text[count].setHint(player.get(col-1).values.get(row).toString());
                    text[count].setOnFocusChangeListener(textFocus);
                    text[count].setId(count);
                    if(row == 4){ //Factory row
                        factoryIds.add(count);
                        text[count].setFilters(new InputFilter[]{ new InputFilterMinMax("0" , "1"), new InputFilter.LengthFilter(FACTORY_MAX_LENGTH)});
                        //text[count].addTextChangedListener(getTextWatcher((EditText)text[count]));
                    }
                }
                text[count].setPadding(50, 25, 10, 25);
                grid.addView(text[count]);
            }
        }
    }

    private void addImage(TextView textView, int row) {
        switch (row) {
            case 0:
                Drawable drP = getResources().getDrawable(R.drawable.player);
                Bitmap bitmapP = ((BitmapDrawable) drP).getBitmap();
// Scale it to 50 x 50
                Drawable dP = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmapP, 75, 100, true));
                textView.setCompoundDrawablesWithIntrinsicBounds(dP, null, null, null);
                break;
            case 1:
                Drawable dr = getResources().getDrawable(R.drawable.popularity);
                Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
// Scale it to 50 x 50
                Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 100, 100, true));
                textView.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
                break;
            case 2:
                Drawable drS = getResources().getDrawable(R.drawable.stars);
                Bitmap bitmapS = ((BitmapDrawable) drS).getBitmap();
// Scale it to 50 x 50
                Drawable dS = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmapS, 100, 100, true));
                textView.setCompoundDrawablesWithIntrinsicBounds(dS, null, null, null);
                break;
            case 3:
                Drawable drT = getResources().getDrawable(R.drawable.territory);
                Bitmap bitmapT = ((BitmapDrawable) drT).getBitmap();
// Scale it to 50 x 50
                Drawable dT = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmapT, 85, 85, true));
                textView.setCompoundDrawablesWithIntrinsicBounds(dT, null, null, null);
                break;
            case 4:
                Drawable drF = getResources().getDrawable(R.drawable.factory);
                Bitmap bitmapF = ((BitmapDrawable) drF).getBitmap();
// Scale it to 50 x 50
                Drawable dF = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmapF, 100, 100, true));
                textView.setCompoundDrawablesWithIntrinsicBounds(dF, null, null, null);
                break;
            case 5:
                Drawable drR = getResources().getDrawable(R.drawable.resources);
                Bitmap bitmapR = ((BitmapDrawable) drR).getBitmap();
// Scale it to 50 x 50
                Drawable dR = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmapR, 110, 100, true));
                textView.setCompoundDrawablesWithIntrinsicBounds(dR, null, null, null);
                break;
            case 6:
                Drawable drSt = getResources().getDrawable(R.drawable.structure);
                Bitmap bitmapSt = ((BitmapDrawable) drSt).getBitmap();
// Scale it to 50 x 50
                Drawable dSt = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmapSt, 100, 100, true));
                textView.setCompoundDrawablesWithIntrinsicBounds(dSt, null, null, null);
                break;
            case 7:
                Drawable drM = getResources().getDrawable(R.drawable.money);
                Bitmap bitmapM = ((BitmapDrawable) drM).getBitmap();
// Scale it to 50 x 50
                Drawable dM = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmapM, 110, 110, true));
                textView.setCompoundDrawablesWithIntrinsicBounds(dM, null, null, null);
                break;
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
