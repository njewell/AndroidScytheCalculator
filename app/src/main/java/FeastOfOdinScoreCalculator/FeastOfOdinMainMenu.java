package FeastOfOdinScoreCalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import GameScoreCalculator.R;
import ScytheScoreCalculator.InputFilterMinMax;

public class FeastOfOdinMainMenu extends AppCompatActivity{
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private static final int MAX_LENGTH = 1;
    private EditText editText;


    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R. layout.scythe_main_menu);

        editText = (EditText)findViewById(R.id.editText);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setFilters(new InputFilter[]{ new InputFilterMinMax("1" , "4")});
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, GameAttributes.class);
        String message = editText.getText().toString();
        if(!message.equals("")) {
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }

    }
}
