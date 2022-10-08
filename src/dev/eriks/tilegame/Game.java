/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame;

import dev.eriks.tilegame.display.Display;
import dev.eriks.tilegame.gfx.Assets;
import dev.eriks.tilegame.gfx.GameCamera;
import dev.eriks.tilegame.gfx.ImageLoader;
import dev.eriks.tilegame.gfx.SpriteSheet;
import dev.eriks.tilegame.input.KeyManager;
import dev.eriks.tilegame.input.MouseManager;
import dev.eriks.tilegame.states.CustomGameState;
import dev.eriks.tilegame.states.GameState;
import dev.eriks.tilegame.states.MenuState;
import dev.eriks.tilegame.states.State;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author eriks
 */
public class Game implements Runnable {

    private Display display;
    private int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //States
    public State gameState;
    public State gameState2;
    public State menuState;
    public State customGameState;
    public State settingsState;

    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;
    //Camera
    private GameCamera gameCamera;

    //
    private Handler handler;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    private void init() throws IOException, FileNotFoundException, InterruptedException, LineUnavailableException, UnsupportedAudioFileException {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);

        gameState = new GameState(handler);

        menuState = new MenuState(handler);
        State.setState(menuState);
    }

    private void tick() {
        keyManager.tick();

        if (State.getState() != null) {
            State.getState().tick();
        }
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //clear screen
        g.clearRect(0, 0, width, height);
        //Draw Here

        if (State.getState() != null) {
            State.getState().render(g);
        }

        bs.show();
        g.dispose();
    }

    public void run() {
        try {
            init();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

        int fps = 60; //Frames Per Second
        double timePerTick = 1000000000 / fps;
        double d = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            d += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (d >= 1) {
                tick();
                render();
                ticks++;
                d--;
            }

            if (timer >= 1000000000) {
                ticks = 0;
                timer = 0;
            }

        }
        stop();
    }

    public State getGameState() {
        return gameState;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
