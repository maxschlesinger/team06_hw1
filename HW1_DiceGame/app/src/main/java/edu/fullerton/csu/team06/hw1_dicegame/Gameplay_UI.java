package edu.fullerton.csu.team06.hw1_dicegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Random;

public class Gameplay_UI extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay__ui);

        // Get the Intent that started this activity
        Intent intent = getIntent();

        spinner = (Spinner)findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.dice_values,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if  (position > 1) {
                    Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_SHORT).show();
                    Player_choose(parent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /** Called when the user taps either of the CPU dice */
    public void CPU_roll(View view) {
        //System.out.println("CPU rolls 2 dice");
        Toast.makeText(getBaseContext(),"CPU rolls 2 dice",Toast.LENGTH_SHORT).show();

        // perform roll and get random number from 1-6 exclusive back
        int roll_value = simulateSingleDiceRoll();

        // for now set button text to the roll value
        ((Button)view).setText(Integer.toString(roll_value));
    }

    /** Called when the player selects a value for the first player dice */
    public void Player_choose(View view) {
        //System.out.println("Player chooses first dice");
        Toast.makeText(getBaseContext(),"Player chooses first dice",Toast.LENGTH_SHORT).show();
    }

    /** Called when the player taps the second player dice */
    public void Player_roll(View view) {
        //System.out.println("Player rolls second dice");
        Toast.makeText(getBaseContext(),"Player rolls second dice",Toast.LENGTH_SHORT).show();

        // perform roll and get random number from 1-6 exclusive back
        int roll_value = simulateSingleDiceRoll();

        // for now set button text to the roll value
        ((Button)view).setText(Integer.toString(roll_value));
    }

    /** Called to calculate/simulate a dice roll, returns a value of 1, 2, 3, 4, 5, or 6 */
    private int simulateSingleDiceRoll(){
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}
