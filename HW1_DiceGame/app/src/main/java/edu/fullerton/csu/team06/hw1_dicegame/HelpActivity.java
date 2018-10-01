package edu.fullerton.csu.team06.hw1_dicegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener {

    String HOW_TO_WIN_TEXT = "How to win\n\n" +
            "\u0009\u0009\u2022 The game consists of 2 players.\n" +
            "\u0009\u0009\u0009You and a CPU opponent\n" +
            "\u0009\u0009\u2022 To win, have the highest score between players.\n" +
            "\u0009\u0009\u2022 How score is calculated:\n" +
            "\u0009\u0009\u0009\u0009a. 2022 Take the total of 2 rolled dice\n" +
            "\u0009\u0009\u0009\u0009b. Divide that value by 6\n" +
            "\u0009\u0009\u0009\u0009c. The remainder will be the player's score.\n";
    String HOW_TO_PLAY_TEXT = "How to play\n\n" +
            "\u0009\u00091. Click \"Play\"\n" +
            "\u0009\u00092. Select each dice to roll it starting with the CPU\n" +
            "\u0009\u00093. Select a value you want to start with for the first\n" +
            "\u0009\u0009\u0009dice, remembering that this will be added to\n" +
            "\u0009\u0009\u0009whatever value the second dice is\n" +
            "\u0009\u00094. Roll your final dice\n" +
            "\u0009\u00095. Good luck\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SpannableString howToWinFormatted = new SpannableString(HOW_TO_WIN_TEXT);
        howToWinFormatted.setSpan(new RelativeSizeSpan(2f), 0, 10, 0);

        SpannableString howToPlayFormatted = new SpannableString(HOW_TO_PLAY_TEXT);
        howToPlayFormatted.setSpan(new RelativeSizeSpan(2f), 0, 11, 0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        TextView howToWinTextView = findViewById(R.id.how_to_win);
        howToWinTextView.setText(howToWinFormatted);
        TextView howToPlayTextView = findViewById(R.id.how_to_play);
        howToPlayTextView.setText(howToPlayFormatted);

        Button playButton = findViewById(R.id.play_button_from_help);
        playButton.setOnClickListener(this);

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_button_from_help: {
                Intent playIntent = new Intent(this, GameplayActivity.class);
                startActivity(playIntent);
                break;
            }
            case R.id.back_button: {
                finish();
                break;
            }
            default: {
                break;
            }
        }

    }
}
