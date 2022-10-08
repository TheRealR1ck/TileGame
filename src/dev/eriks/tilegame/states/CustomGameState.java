/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.states;

import dev.eriks.tilegame.Game;

import dev.eriks.tilegame.Handler;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author eriks
 */
public class CustomGameState {

    private boolean difficulty, monster1, monster2, monster3, itemRandom, layoutRandom, spawnRandom;

    public CustomGameState(Handler handler, boolean difficulty, boolean monster1,
            boolean monster2, boolean monster3, boolean itemRandom, boolean layoutRandom, boolean spawnRandom) {
        this.difficulty = difficulty;
        this.monster1 = monster1;
        this.monster2 = monster2;
        this.monster3 = monster3;
        this.itemRandom = itemRandom;
        this.layoutRandom = layoutRandom;
        this.spawnRandom = spawnRandom;
    }

    public boolean isDifficulty() {
        return difficulty;
    }

    public boolean isMonster1() {
        return monster1;
    }

    public boolean isMonster2() {
        return monster2;
    }

    public boolean isMonster3() {
        return monster3;
    }

    public boolean isItemRandom() {
        return itemRandom;
    }

    public boolean isLayoutRandom() {
        return layoutRandom;
    }

    public boolean isSpawnRandom() {
        return spawnRandom;
    }

}
