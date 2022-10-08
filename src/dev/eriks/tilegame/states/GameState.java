/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.states;

import dev.eriks.tilegame.Game;
import dev.eriks.tilegame.Handler;
import dev.eriks.tilegame.entities.creatures.Player;
import dev.eriks.tilegame.entities.statics.Tree;
import dev.eriks.tilegame.gfx.Assets;
import dev.eriks.tilegame.tiles.Tile;
import dev.eriks.tilegame.worlds.TheWorlds;
import dev.eriks.tilegame.worlds.World;
import java.awt.Graphics;

/**
 *
 * @author eriks
 */
public class GameState extends State{


    private World world;
    private TheWorlds theWorlds = new TheWorlds();
    private String worldPath;
    public Handler handler;
    //add new parameter, worldPath
    public GameState(Handler handler){
        super(handler);
        this.handler = handler;
        worldPath = theWorlds.getEntrance();
        world = new World(handler, worldPath);
        handler.setWorld(world);
    }
    public GameState(Handler handler, CustomGameState cg){
        super(handler);
        this.handler = handler;
        //checks
        System.out.println("CG.IS LAYOUT = " + cg.isSpawnRandom());
        if(cg.isSpawnRandom() == true){
            worldPath = theWorlds.getRandomStartRoom();
        }
        else{
        worldPath = theWorlds.getEntrance();
        }    
        world = new World(handler, worldPath);
        handler.setWorld(world);
    }
    
    public void setTheWorldPath(String path){
        worldPath = path;
    }
    
    public void changeWorld() throws InterruptedException{
        //loading screen
        //displayLoadingScreen();
        world = new World(handler,worldPath);
        handler.setWorld(world);
    }
    public void displayLoadingScreen() throws InterruptedException{
        Thread.sleep(500);
    }
    @Override
    public void tick() {
        world.tick();
        
        // scroll game.getGameCamera().move(1, 1);
    }

    @Override
    public void render(Graphics g) {
        world.render(g);

    }
    
}
