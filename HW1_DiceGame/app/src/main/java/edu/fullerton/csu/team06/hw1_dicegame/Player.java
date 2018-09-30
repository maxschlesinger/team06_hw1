package edu.fullerton.csu.team06.hw1_dicegame;

import java.util.Random;

public class Player {
    /**    variables    */
    protected int roll1;
    protected int roll2;
    protected int score;
    protected String name;
    protected Boolean isAI;
    protected Boolean isTurnComplete;

    /**    Default Constructor   */
    public Player(){
        this.roll1 = -1;
        this.roll2 = -1;
        this.score = -1;
        this.isAI = false;
        this.isTurnComplete = false;
    }
    /**    Parameterized Constructor   */
    public Player(int roll1, int roll2, int score, String name, Boolean isAI, Boolean isTurnComplete) {
        this.roll1 = roll1;
        this.roll2 = roll2;
        this.score = score;
        this.name = name;
        this.isAI = isAI;
        this.isTurnComplete = isTurnComplete;
    }

    /**    getter/setter methods    */
    public int getRoll1() {
        return roll1;
    }
    public void setRoll1(int roll1) {
        this.roll1 = roll1;
    }

    public int getRoll2() {
        return roll2;
    }

    public void setRoll2(int roll2) {
        this.roll2 = roll2;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAI() {
        return isAI;
    }

    public void setAI(Boolean AI) {
        isAI = AI;
    }

    public Boolean getTurnComplete() {
        return isTurnComplete;
    }

    public void setTurnComplete(Boolean turnComplete) {
        isTurnComplete = turnComplete;
    }


    /** public methods */

    /** Roll all dice the player is allowed to roll. Set corresponding roll values.
     * For AI that will be 1 and 2, for Human it will be the #2 roll.
     * This also assumes that the player is going to choose the first dice value
     * prior to being able to roll the second dice. */
    public void rollDice(){
        // if player has already rolled then don't allow another roll
        // OR if this is a human player, don't roll if the first dice roll is not set/chosen
        if(this.isTurnComplete ||
                (!this.isAI && this.roll1 <= 0)){
            return;
        }

        // create random generator
        Random random = new Random();

        // if AI player, set roll 1
        if(this.isAI)
            this.roll1 = random.nextInt(6) + 1;

        this.roll2 = random.nextInt(6) + 1;

        // after rolling, the players turn is complete
        this.isTurnComplete = true;
    }
}

