package edu.fullerton.csu.team06.hw1_dicegame;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
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
                startActivityForResult(intent, 1);
                break;
            }
            case R.id.helpButton: {
                Intent intent = new Intent(this, HelpActivity.class);
                startActivityForResult(intent, 1);
                break;
            }
            case R.id.aboutButton: {
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Integer result = data.getIntExtra("result", 0);
                if (result == 1) {
                    // Restart game/Play Again
                    Intent intent = new Intent(this, GameplayActivity.class);
                    Game newGame = new Game();
                    intent.putExtra("gameObject", newGame);
                    startActivityForResult(intent, 1);
                }
            }
        }
    }
}
