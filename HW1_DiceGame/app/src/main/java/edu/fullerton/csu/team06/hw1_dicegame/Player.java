package edu.fullerton.csu.team06.hw1_dicegame;

public class Player {

    //Variables
    protected int roll1;
    protected int roll2;
    protected int score;
    protected String name;
    protected Boolean isAI;
    protected Boolean isTurnComplete;

    //Default constructor
    public Player(){
        this.roll1 = -1;
        this.roll2 = -1;
        this.score = -1;
        this.isAI = false;
        this.isTurnComplete = false;
    }

    //Parameterized Constructor
    public Player(String name, int roll1, int roll2, int score, boolean isAI, boolean isTurnComplete){
        this.name = name;
        this.roll1 = roll1;
        this.roll2 = roll2;
        this.score = score;
        this.isAI = isAI;
        this.isTurnComplete = isTurnComplete;
    }

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
}
