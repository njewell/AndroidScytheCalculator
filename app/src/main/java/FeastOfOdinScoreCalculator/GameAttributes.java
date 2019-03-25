package FeastOfOdinScoreCalculator;

import android.app.Activity;
import android.content.Intent;
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
import ScytheScoreCalculator.ScytheMainMenu;

public class GameAttributes extends Activity {

    private final String  category[] = {"Player", "Ships", "Emigrations", "Exploration", "Sheds/Houses", "Sheep/Cattle", "Occupations", "Silver", "Final Income", "English Crown",
    "Home Board", "Thing Penalty", "Total"};
    int numOfPlayers;
    int numOfCol;
    private int numOfRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);


        Intent intent = getIntent();
        String message = intent.getStringExtra(ScytheMainMenu.EXTRA_MESSAGE);
        numOfPlayers = Integer.parseInt(message);
        numOfCol = numOfPlayers + 1;
        numOfRow = category.length;

        //createPlayerObjects();

        //grid = this.findViewById(R.id.GridLayout1);
        //grid.setColumnCount(numOfCol);
        //createScoreGrid();
    }


}
