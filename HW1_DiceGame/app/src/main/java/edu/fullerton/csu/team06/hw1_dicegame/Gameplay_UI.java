package edu.fullerton.csu.team06.hw1_dicegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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
    }
}
