package edu.fullerton.csu.team06.hw1_dicegame;

import android.app.Activity;
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

public class GameplayActivity extends AppCompatActivity implements OnClickListener {
    ArrayAdapter<CharSequence> adapter;

    Game GAME;

    Button CPU_D1_UI;
    Button CPU_D2_UI;
    TextView CPU_SCORE_UI;
    Spinner PLAYER_D1_UI;
    Button PLAYER_D2_UI;
    TextView PLAYER_SCORE_UI;
    TextView WINNER_UI;
    TextView PLAYER_SCORE_LABEL;
    TextView CPU_SCORE_LABEL;
    Button PLAY_AGAIN_BUTTON;

    // End simulated player object properties

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        // Get the Intent that started this activity
        Intent prevIntent = this.getIntent();
        GAME = (Game) prevIntent.getSerializableExtra("gameObject");

        // Get the Spinner object
        PLAYER_D1_UI = findViewById(R.id.PLYR_D1);
        adapter = ArrayAdapter.createFromResource(this,R.array.dice_values,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        PLAYER_D1_UI.setAdapter(adapter);

        // setup the cpu button callbacks for rolls
        CPU_D1_UI = findViewById(R.id.CPU_D1);
        CPU_D1_UI.setOnClickListener(this);
        CPU_D2_UI = findViewById(R.id.CPU_D2);
        CPU_D2_UI.setOnClickListener(this);

        // setup the player roll 2 button callbacks for rolls
        PLAYER_D2_UI = findViewById(R.id.PLYR_D2);
        PLAYER_D2_UI.setOnClickListener(this);

        // get the TextViews for score
        CPU_SCORE_LABEL = findViewById(R.id.CPU_Score_lbl);
        PLAYER_SCORE_LABEL = findViewById(R.id.player_score_label);
        CPU_SCORE_LABEL.setText(GAME.getCpuPlayer().getName() + " Score: ");
        PLAYER_SCORE_LABEL.setText(GAME.getHumanPlayer().getName() + " Score: ");
        CPU_SCORE_UI = findViewById(R.id.CPU_Score_value);
        PLAYER_SCORE_UI = findViewById(R.id.Player_Score_value);

        // get Play Again Button
        PLAY_AGAIN_BUTTON = findViewById(R.id.play_again_button);
        PLAY_AGAIN_BUTTON.setOnClickListener(this);
        PLAY_AGAIN_BUTTON.setVisibility(View.INVISIBLE);

        // get winner TextViews
        WINNER_UI = findViewById(R.id.winner_textview);

        // setup the player spinner dice for choosing value
        PLAYER_D1_UI.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onClick(parent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // perform update to ui components for this activity
        updateUI();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.CPU_D1:
            case R.id.CPU_D2:
                /** Called when the user taps either of the CPU dice */
                GAME.getCpuPlayer().rollDice();
                break;
            case R.id.PLYR_D1:
                /** Called when the player selects a value for the first player dice */
                PLAYER_D1_UI = findViewById(R.id.PLYR_D1);
                String uiValue_Str =  PLAYER_D1_UI.getSelectedItem().toString();
                try {
                    GAME.getHumanPlayer().setDice1(Integer.parseInt(uiValue_Str));
                } catch (NumberFormatException e) {
                    // handle the scenario that user selects "-"
                    // set value to -1
                    GAME.getHumanPlayer().setDice1(-1);
                }
                break;
            case R.id.PLYR_D2:
                /** Called when the player taps the second player dice */
                GAME.getHumanPlayer().rollDice();
                break;
            case R.id.play_again_button:
                Intent returnIntent = new Intent();
                // 1 means play again
                returnIntent.putExtra("result", 1);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                break;
            default:
                break;
        }
        // perform update to ui components for this activity
        updateUI();
    }

    public void updateUI() {
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

        // determine state of activity
        if (GAME.getCpuPlayer().getDice1() > 0 && GAME.getCpuPlayer().getDice2() > 0) {
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
        }
        if (GAME.getHumanPlayer().getDice1() > 0) {
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
        }
        if (GAME.getHumanPlayer().getDice2() > 0) {
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
            Player winningPlayer = GAME.getWinner();
            if (winningPlayer == null) {
                WINNER_UI.setText("It's a tie!");
            } else {
                WINNER_UI.setText(winningPlayer.getName() + " Wins!");
            }
            PLAY_AGAIN_BUTTON.setVisibility(View.VISIBLE);
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
            CPU_D1_UI.setText(Integer.toString(GAME.getCpuPlayer().getDice1()));
            CPU_D2_UI.setEnabled(false);
            CPU_D2_UI.setText(Integer.toString(GAME.getCpuPlayer().getDice2()));
            CPU_SCORE_UI.setText(Integer.toString(GAME.getCpuPlayer().getScore()));
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
            PLAYER_D1_UI.setSelection(GAME.getHumanPlayer().getDice1()); // add offset of default option
        }

        // cpu ui components SET E
        if (ui_state < 3) {
            PLAYER_D2_UI.setText("-");
            PLAYER_SCORE_UI.setText("-");
        } else {
            PLAYER_D2_UI.setText(Integer.toString(GAME.getHumanPlayer().getDice2()));
            PLAYER_SCORE_UI.setText(Integer.toString(GAME.getHumanPlayer().getScore()));
        }

    }
}
