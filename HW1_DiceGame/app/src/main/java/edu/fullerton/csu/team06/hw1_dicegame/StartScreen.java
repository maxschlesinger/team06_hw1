package edu.fullerton.csu.team06.hw1_dicegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class StartScreen extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }

    /** Called when the user taps the Play button */
    public void playGame(View view) {
        Intent intent = new Intent(this, Gameplay_UI.class);
        startActivity(intent);
    }

}
