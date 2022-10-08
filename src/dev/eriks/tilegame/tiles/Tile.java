/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author eriks
 */
public class Tile {
    
    //Static stuff
    
    public static Tile[] tiles = new Tile[256];
    public static Tile blackTile = new BlackTile(0);
    public static Tile grassTile = new GrassTile(1);
    public static Tile dirtTile = new DirtTile(2);
    public static Tile rockTile = new RockTile(3);
    public static Tile blueCarpetTile = new BlueCarpet(4);
    public static Tile redCarpetTile = new RedCarpet(5);
    public static Tile greenCarpetTile = new GreenCarpet(6);
    public static Tile grayCheckerTile = new GrayChecker(7);
    
    
    //Stairs
    public static Tile uTdStairs = new uTdStairsTile(8);
    public static Tile dTuStairs = new dTuStairsTile(9);
    public static Tile lTrStairs = new lTrStairsTile(10);
    public static Tile rTlStairs = new rTlStairsTile(11);
    public static Tile horizontalStairs = new horizontalStairsTile(12);
    public static Tile verticalStairs = new verticalStairsTile(13);
    
    //New Tiles
    public static Tile garage1Tile = new Garage1Tile(14);
    public static Tile garage2Tile = new Garage2Tile(15);
    public static Tile garage3Tile = new Garage3Tile(16);
    
    public static Tile kitchen1Tile = new Kitchen1Tile(17);
    public static Tile kitchen2Tile = new Kitchen2Tile(18);
    
    
    //Class
    
    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
    
    protected BufferedImage texture;
    protected final int id;
    
    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;
        
        tiles[id] = this;
    }
    
    public void tick(){
        
    }
    
    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }
    
    public boolean isSolid(){
        return false;
    }
    
    public boolean isLoadable(){
        return false;
    }
    
    public int getId(){
        return id;
    }
    
    
    
}
