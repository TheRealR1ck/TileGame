/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame;

import dev.eriks.tilegame.display.Display;
import dev.eriks.tilegame.gfx.GameCamera;
import dev.eriks.tilegame.input.KeyManager;
import dev.eriks.tilegame.input.MouseManager;
import dev.eriks.tilegame.states.GameState;
import dev.eriks.tilegame.states.State;
import dev.eriks.tilegame.worlds.World;

/**
 *
 * @author eriks
 */
public class Handler {

    private Game game;
    private World world;
    private Display display;

    public Handler(Game game) {
        this.game = game;
    }

    public GameState getGameState() {
        return (GameState) game.getGameState();
    }

    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Display getDisplay() {
        return display;
    }

}
