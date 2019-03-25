package GameScoreCalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import FeastOfOdinScoreCalculator.FeastOfOdinMainMenu;
import GameScoreCalculator.R;
import ScytheScoreCalculator.ScytheMainMenu;

public class MainMenu extends AppCompatActivity{
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private static final int MAX_LENGTH = 1;
    private EditText editText;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R. layout.main_menu);
    }

    public void ScytheScoreCalculator(View view){
        Intent intent = new Intent(this, ScytheMainMenu.class);
        startActivity(intent);
}

    public void FeastOfOdinScoreCalcutor(View view){
        Intent fooIntent = new Intent(this, FeastOfOdinMainMenu.class);
        startActivity(fooIntent);
    }
}
