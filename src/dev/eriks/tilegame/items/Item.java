/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.items;

import dev.eriks.tilegame.Handler;
import dev.eriks.tilegame.gfx.Assets;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author eriks
 */
public class Item {
    
    //Handler
    
    public static Item[] items = new Item[100];
    public static Item firstAid = new Item(Assets.firstAid, "First Aid", 0);
    public static Item rockP = new Item(Assets.rockP, "Rock Piece", 1);
    public static Item stoneP = new Item(Assets.stoneP, "Stone Piece", 2);
    public static Item stoneNrockP = new Item(Assets.stoneNrockP,"Artifact", 3);
    
    //Class
    
    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;
    
    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;
    
    protected Rectangle bounds;
    protected Rectangle loadableBounds;
    
    protected int x, y, count;
    protected boolean pickedUp = false;
    public Item(BufferedImage texture, String name, int id){
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;
        
        bounds = new Rectangle(x, y, ITEMWIDTH,ITEMHEIGHT);
        
        items[id] = this;
    }

    public void tick(){
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)  && handler.getKeyManager().interact){
            pickedUp = true;
            handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
        }
        /*
        else if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(loadableBounds)  && handler.getKeyManager().interact){
            //load world
        }
        */
    }
    
    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }
    
    public void render(Graphics g){
        if(handler == null)
            return;
        render(g,(int)(x - handler.getGameCamera().getxOffset()),(int)(y - handler.getGameCamera().getyOffset()));
    }
    
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }
    
    public Item createNew(int count){
        Item i = new Item(texture, name, id);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }
    
    public Item createNew(int x, int y){
        Item i = new Item(texture, name, id);
        i.setPosition(x, y);
        
        return i;
    }
    
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId(){
        return id;
    }
    /*
    public void setId(int id){
        this.id = id;
    }
    */
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }
    
    
    
}
