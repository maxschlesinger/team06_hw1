package edu.fullerton.csu.team06.hw1_dicegame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
public class AboutActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button playButton = findViewById(R.id.play_button_from_about);
        playButton.setOnClickListener(this);

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_button_from_about: {
                Intent playIntent = new Intent(this, GameplayActivity.class);
                playIntent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                Game newGame = new Game();
                playIntent.putExtra("gameObject", newGame);
                startActivity(playIntent);
                finish();
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
