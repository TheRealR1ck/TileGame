/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.states;

import dev.eriks.tilegame.Game;
import dev.eriks.tilegame.Handler;
import java.awt.Graphics;

/**
 *
 * @author eriks
 */
public abstract class State {
    
    private static State currentState = null;
    
    public static void setState(State state){
        currentState = state;
        
    }
    
    public static State getState(){
        return currentState;
    }
    
    //Class
    protected Handler handler ;
    protected Game game;
    
    public State(Handler handler){
        this.handler = handler;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);

}
