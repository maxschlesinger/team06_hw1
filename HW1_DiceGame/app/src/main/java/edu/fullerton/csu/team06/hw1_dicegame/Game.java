package edu.fullerton.csu.team06.hw1_dicegame;

import java.io.Serializable;

/** For now, hard coding the 2 players in the game so we stay in scope.
 * Later on we can consider utilizing a settings class that may hold items like:
 * - # of players
 * - # of rounds
 * - difficulty
 *
 * Also, to start, only checking if the turn is complete to determine if a game is considered over.
 * Later this may depend on rounds instead.
 *
 * Also to stay in scope, creating the two players in the game constructor. This could be done
 * quite a few ways if we expand this and that will take some discussion. */
public class Game implements Serializable{

    protected Player cpuPlayer;
    protected Player humanPlayer;
    protected boolean isGameOver;


    public Game(){
        this.cpuPlayer = new Player(true);
        this.humanPlayer = new Player(false);
        this.isGameOver = false;
    }

    /** retrieve the cpu player of the game */
    public Player getCpuPlayer(){ return this.cpuPlayer; }

    /** retrieve the human player of the game */
    public Player getHumanPlayer(){ return this.humanPlayer; }

    /** Called to check if the game is completed. The game must be complete
     * in order to get a winner. */
    public boolean getIsGameOver(){
        return this.cpuPlayer.getTurnComplete() && this.humanPlayer.getTurnComplete();
    }

    /** The game must be over to get a winner! This method will calculate a winner.
     *
     * @return returns the winning player, if the players tied, it will return null.
     */
    public Player getWinner(){
        // make sure the game is over
        if(!getIsGameOver())
            throw new UnsupportedOperationException("Developer, make sure the game is complete prior to trying to retrieve a winner.");

        // just making it easier to read by grabbing these and storing them in a variable
        int cpuScore = this.cpuPlayer.getScore();
        int humanScore = this.humanPlayer.getScore();

        // if the scores are equal, return null
        if(cpuScore == humanScore)
            return null;
        else return cpuScore > humanScore ? this.cpuPlayer : this.humanPlayer; // return the player object with the highest score
    }
}
