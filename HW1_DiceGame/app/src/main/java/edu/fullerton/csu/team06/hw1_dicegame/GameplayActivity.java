package edu.fullerton.csu.team06.hw1_dicegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class GameplayActivity extends AppCompatActivity implements OnClickListener {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        // Get the Intent that started this activity
        spinner = findViewById(R.id.PLYR_D1);
        adapter = ArrayAdapter.createFromResource(this,R.array.dice_values,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        // setup the cpu button callbacks for rolls
        Button cpu_d1 = findViewById(R.id.CPU_D1);
        cpu_d1.setOnClickListener(this);
        Button cpu_d2 = findViewById(R.id.CPU_D2);
        cpu_d2.setOnClickListener(this);

        // setup the player spinner dice for choosing value
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if  (position > 1) {
                    Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " selected", Toast.LENGTH_SHORT).show();
                    onClick(parent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // setup the player button callback for roll
        Button player_d1 = findViewById(R.id.PLYR_D2);
        player_d1.setOnClickListener(this);

        /*
        * After creation is done, update function should be called  to ensure proper ui components
         * are enabled/disabled and proper values are restored, especially if returning to previous
         * game.*/

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.CPU_D1:
            case R.id.CPU_D2:
                /** Called when the user taps either of the CPU dice */

                /*
                * CPU roll sequence will go here
                */

                // Temporary toast for acknowledgment - Remove once sequence is finished
                Toast.makeText(getBaseContext(),"CPU rolls 2 dice",Toast.LENGTH_SHORT).show();

                /*
                * perform update - this should disable the cpu dice buttons and enable the
                * player's first dice for choosing
                 */
                break;

            case R.id.PLYR_D1:
                /** Called when the player selects a value for the first player dice */

                /*
                 Player choose sequence will go here
                  */

                // Temporary toast for acknowledgment - Remove once sequence is finished
                Toast.makeText(getBaseContext(),"Player chooses first dice",Toast.LENGTH_SHORT).show();

                /*
                 * perform update - this should enable the player's second dice button for rolling.
                 * This shouldn't be disabled right away since the player may wish to change his
                 * choice, as long as the second dice has not been rolled yet.
                 */
                break;
            case R.id.PLYR_D2:
                /** Called when the player taps the second player dice */

                /*
                 Player roll sequence will go here
                  */

                // Temporary toast for acknowledgment - Remove once sequence is finished
                Toast.makeText(getBaseContext(),"Player rolls second dice",Toast.LENGTH_SHORT).show();

                /*
                 * perform update - this should disable both of the player's dice buttons
                 */

                break;
        }
    }

}
