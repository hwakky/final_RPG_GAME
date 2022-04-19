package controller;
import game.Game;

//multi task 
public class timer implements Runnable{
    Game game;
    public timer(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
 
    }
}
