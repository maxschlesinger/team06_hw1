package edu.fullerton.csu.team06.hw1_dicegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameplayActivity extends AppCompatActivity implements OnClickListener {
    ArrayAdapter<CharSequence> adapter;

    /*
    Simulating player objects with just integers to show functionality of update
     */
    public int cpu1 = -1;
    public int cpu2 = -1;
    public int plr1 = -1;
    public int plr2 = -1;

    Button CPU_D1_UI;
    Button CPU_D2_UI;
    TextView CPU_SCORE_UI;
    Spinner PLAYER_D1_UI;
    Button PLAYER_D2_UI;
    TextView PLAYER_SCORE_UI;
    Spinner PLAYER_D1_SPINNER;

    // End simulated player object properties

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        // Get the Intent that started this activity
        Intent prevIntent = this.getIntent();
        Game game = (Game) prevIntent.getSerializableExtra("gameObject");

        // Get the Spinner object
        PLAYER_D1_SPINNER = findViewById(R.id.PLYR_D1);
        adapter = ArrayAdapter.createFromResource(this,R.array.dice_values,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        PLAYER_D1_SPINNER.setAdapter(adapter);

        // setup the cpu button callbacks for rolls
        Button CPU_D1_UI = findViewById(R.id.CPU_D1);
        CPU_D1_UI.setOnClickListener(this);
        CPU_D2_UI = findViewById(R.id.CPU_D2);
        CPU_D2_UI.setOnClickListener(this);

        // setup the player spinner dice for choosing value
        PLAYER_D1_SPINNER.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if  (position > 0) {
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

        // perform update to ui components for this activity
        updateUI();

    }

    @Override
    public void onClick(View v) {
        // For simulating player's dice roll only
        Random r = new Random();
        //

        switch (v.getId()) {
            case R.id.CPU_D1:
            case R.id.CPU_D2:
                /** Called when the user taps either of the CPU dice */

                /*
                * CPU roll sequence will go here
                */

                // simulating cpu dice roll until construction complete for player object controls
                    // assign random values to the cpu player
                    this.cpu1 = r.nextInt(6)+1;
                    this.cpu2 = r.nextInt(6)+1;
                // END simulated code
                break;

            case R.id.PLYR_D1:
                /** Called when the player selects a value for the first player dice */

                /*
                 Player choose sequence will go here
                  */

                // simulating player choosing first dice until construction complete for player object controls
                    // retrieve dice value
                    PLAYER_D1_SPINNER = findViewById(R.id.PLYR_D1);
                    String uiValue_Str =  PLAYER_D1_SPINNER.getSelectedItem().toString();
                    try {
                        this.plr1 = Integer.parseInt(uiValue_Str);
                    } catch (NumberFormatException e) {
                        // handle the scenario that user selects "-"
                        // set value to -1
                        this.plr1 = -1;
                    }
                // END simulated code
                break;
            case R.id.PLYR_D2:
                /** Called when the player taps the second player dice */

                /*
                 Player roll sequence will go here
                  */

                // simulating player choosing first dice until construction complete for player object controls
                    // assign random values to the cpu player
                    this.plr2 = r.nextInt(6)+1;
                // END simulated code
                break;
        }
        // perform update to ui components for this activity
        updateUI();
    }

    public void updateUI() {
        // retrieve cpu player object

        // use activity's simulated player object dice values
        int cpu_d1_value = this.cpu1;
        int cpu_d2_value = this.cpu2;
        int player_d1_value = this.plr1;
        int player_d2_value = this.plr2;
// determine score from simulated values (don't worry about dice being -1)
        int cpu_score_value = (cpu_d1_value + cpu_d2_value) % 6;
        int player_score_value = (player_d1_value + player_d2_value) % 6;

// END simulated value population

        /*
         * State 0 - New game
         *      to do:  - enable both cpu buttons
         *              - set text of buttons to default '-'
         *              - set cpu score to default '-'
         *              - disable player spinner and player button
         *              - set text of player ui components to default '-'
         *              - set player score to default '-'
         */
        int ui_state = 0;
// Toast messages are for troubleshooting purposes only.  Intention is to remove once validated
        String toastMsg = "select to roll CPU dice";

        // determine state of activity
        if (cpu_d1_value > 0 && cpu_d2_value > 0) {
            /*
             * State 1 - CPU dice values determined
             *      to do:  - disable both cpu buttons
             *              - set text of buttons to that of the player dice values
             *              - set cpu score to cpu score
             *              - enable player spinner and player button
             *              - set text of player ui components to default '-'
             *              - set player score to default '-'
             */
            ui_state = 1;
// Toast messages are for troubleshooting purposes only.  Intention is to remove once validated
            toastMsg = "choose your 1st dice";
        }
        if (player_d1_value > 0) {
            /*
             * State 2 - Player dice 1 value determined
             *      to do:  - disable both cpu buttons
             *              - set text of buttons to that of the player dice values
             *              - set cpu score to cpu score
             *              - enable player spinner and player button
             *              - set text of spinner to that of player dice 1 value
             *              - set text of player dice 2 button to default '-'
             *              - set player score to default '-'
             */
            ui_state = 2;
// Toast messages are for troubleshooting purposes only.  Intention is to remove once validated
            toastMsg = "roll your 2nd dice";
        }
        if (player_d2_value > 0) {
            /*
             * State 3 - Player dice 2 value determined
             *      to do:  - disable both cpu buttons
             *              - set text of buttons to that of the player dice values
             *              - set cpu score to cpu score
             *              - disable player spinner and player button
             *              - set text of player ui components to that of player dice values
             *              - set player score to player score
             */
            ui_state = 3;
            if (player_score_value<cpu_score_value) {
// Toast messages are for troubleshooting purposes only.  Intention is to remove once validated
                toastMsg = "You Lose...";
            }else if (player_score_value>cpu_score_value){
// Toast messages are for troubleshooting purposes only.  Intention is to remove once validated
                toastMsg = "You Win!!";
            }else{
// Toast messages are for troubleshooting purposes only.  Intention is to remove once validated
                toastMsg = "-No Winner-";
            }
        }
        /* STATE TRANSITION BASED ON COMPONENT  4 unique sets of components
         *      UI COMPONENT(s)              0       1       2       3
         *  (A)   cpu default               true   false   false   false  (includes enabled buttons and '-' for button/score
         *  (B) player d1 disabled          true   false   false    true
         *  (C) player d2 disabled          true    true   false    true
         *  (D) player d1 default           true    true   false   false
         *  (E) player score/d2 default     true    true    true   false
         */

        // update the ui components according to state
        // cpu ui components SET A
        if (ui_state < 1) {
            CPU_D1_UI.setEnabled(true);
            CPU_D1_UI.setText("-");
            CPU_D2_UI.setEnabled(true);
            CPU_D2_UI.setText("-");
            CPU_SCORE_UI.setText("-");
        } else {
            CPU_D1_UI.setEnabled(false);
            CPU_D1_UI.setText(Integer.toString(cpu_d1_value));
            CPU_D2_UI.setEnabled(false);
            CPU_D2_UI.setText(Integer.toString(cpu_d2_value));
            CPU_SCORE_UI.setText(Integer.toString(cpu_score_value));
        }

        // player spinner/button enabled properties SET B
        if (ui_state < 1 || ui_state > 2) {
            PLAYER_D1_UI.setEnabled(false);
        } else {
            PLAYER_D1_UI.setEnabled(true);
        }

        // player spinner/button enabled properties SET C
        if (ui_state < 2 || ui_state > 2) {
            PLAYER_D2_UI.setEnabled(false);
        } else {
            PLAYER_D2_UI.setEnabled(true);
        }

        // player d1 button text value SET D
        if (ui_state < 2) {
            PLAYER_D1_UI.setSelection(0); // refers to "-"
        } else {
            PLAYER_D1_UI.setSelection(player_d1_value); // add offset of default option
        }

        // cpu ui components SET E
        if (ui_state < 3) {
            PLAYER_D2_UI.setText("-");
            PLAYER_SCORE_UI.setText("-");
        } else {
            PLAYER_D2_UI.setText(Integer.toString(player_d2_value));
            PLAYER_SCORE_UI.setText(Integer.toString(player_score_value));
        }
        // Temporary toast for acknowledgment - Remove once sequence is finished
        Toast.makeText(getBaseContext(),toastMsg,Toast.LENGTH_LONG).show();

    }

}
