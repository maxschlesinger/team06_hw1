package edu.fullerton.csu.team06.hw1_dicegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class StartScreen extends AppCompatActivity {
    private int Dice1 = -1;
    private int Dice2 = -1;
    private int score = -1;

    String buttonID = "name";

    private boolean isAI = false;
    private boolean turnCompleted = false;

    //TODO: add array to check name. if name is buttonID changes for
    // AI: Computer, 		USER:You
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }
}
