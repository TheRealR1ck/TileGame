package dev.eriks.tilegame;

import dev.eriks.tilegame.display.Display;

/**
 *
 * @author eriks
 */
public class Launcher {
    public static void main(String args[]){
       Game game = new Game("The Estate",800,600);
       game.start();
    }
}
