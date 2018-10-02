package edu.fullerton.csu.team06.hw1_dicegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(this);
        Button helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(this);
        Button aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playButton: {
                Intent intent = new Intent(this, GameplayActivity.class);
                Game newGame = new Game();
                intent.putExtra("gameObject", newGame);
                startActivity(intent);
                break;
            }
            case R.id.helpButton: {
                Intent intent = new Intent(this, HelpActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.aboutButton: {

                break;
            }
            default: {
                break;
            }
        }
    }
}
