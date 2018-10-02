package edu.fullerton.csu.team06.hw1_dicegame;

import java.util.Random;

public class Player {
    /**    variables    */
    protected int dice1;
    protected int dice2;
    protected String name;
    protected Boolean isAI;
    protected Boolean isTurnComplete;

    /**    Default Constructor   */
    public Player(boolean isAI){
        this.dice1 = -1;
        this.dice2 = -1;
        this.isAI = isAI;
        this.isTurnComplete = false;

        // set the players name based on if it's ai or not
        this.name = isAI ? "CPU" : "Player";
    }

    /**    getter/setter methods    */
    public int getDice1() {
        return dice1;
    }

    public void setDice1(int dice1) {
        if(this.isAI)
            throw new UnsupportedOperationException("Cannot set the dice value for AI player. AI must 'rollDice'.");

        this.dice1 = dice1;
    }

    public int getDice2() {
        return dice2;
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

    public boolean getTurnComplete() { return this.isTurnComplete; }

    /** public methods */

    /** Determines if the player is in a state that allows for them to roll.
     * - CPU may roll if turn is not complete.
     * - Human may roll if turn is not complete and the first dice is chosen. */
    public boolean canPlayerRoll(){
        if(this.isAI)
            return !this.getTurnComplete();
        else
            return !this.getTurnComplete() && (this.dice1 != -1);
    }

    /** Roll all dice the player is allowed to roll. Set corresponding roll values.
     * For AI that will be 1 and 2, for Human it will be the #2 roll.
     * This also assumes that the player is going to choose the first dice value
     * prior to being able to roll the second dice. */
    public void rollDice(){
        // make sure the player can roll
        if(!canPlayerRoll()){
            throw new UnsupportedOperationException("This player is not in a state that allows for rolling. Check 'canPlayerRoll()' prior to rolling dice.");
        }

        // create random generator
        Random random = new Random();

        // if AI player, set roll 1
        if(this.isAI)
            this.dice1 = random.nextInt(6) + 1;

        this.dice2 = random.nextInt(6) + 1;

        // after rolling, the players turn is complete
        this.isTurnComplete = true;
    }

    /** Retrieve the score of this player. The players turn must be complete in order
     * to get their score! */
    public int getScore() {
        // make sure the turn is complete
        if(!this.isTurnComplete)
            throw new UnsupportedOperationException("Players turn must be complete before a score can be calculated. Check 'isTurnCompleted'.");

        // get the players score
        // add two dice, take modulus of 6 (remainder after deviding by 6)
        return (this.dice1 + this.dice2) % 6;
    }
}

