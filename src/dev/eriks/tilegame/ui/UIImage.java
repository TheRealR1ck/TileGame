/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author eriks
 */


    
public class UIImage extends UIObject{

    private BufferedImage images;

    
    public UIImage(float x, float y, int width, int height, BufferedImage image) {
        super(x, y, width, height);
        this.images = image;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
            g.drawImage(images, (int) x, (int) y, width, height, null);
    }    

    @Override
    public void onClick() {
        
    }
}

    

