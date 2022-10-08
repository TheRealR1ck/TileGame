/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.worlds;

import dev.eriks.tilegame.Game;
import dev.eriks.tilegame.Handler;
import dev.eriks.tilegame.entities.EntityManager;
import dev.eriks.tilegame.entities.creatures.Player;
import dev.eriks.tilegame.entities.statics.EntranceToLivingRoomDoor;
import dev.eriks.tilegame.entities.statics.EntranceToMudroomDoor;
import dev.eriks.tilegame.entities.statics.GarageToLivingRoomDoor;
import dev.eriks.tilegame.entities.statics.KitchenToLivingRoomDoor;
import dev.eriks.tilegame.entities.statics.LivingRoomToEntranceDoor;
import dev.eriks.tilegame.entities.statics.LivingRoomToGarageDoor;
import dev.eriks.tilegame.entities.statics.LivingRoomToKitchenDoor;
import dev.eriks.tilegame.entities.statics.MudroomToEntranceDoor;
import dev.eriks.tilegame.entities.statics.Rock;
import dev.eriks.tilegame.entities.statics.Tree;
import dev.eriks.tilegame.items.Item;
import dev.eriks.tilegame.items.ItemManager;
import dev.eriks.tilegame.tiles.Tile;
import dev.eriks.tilegame.utils.Utils;
import java.awt.Graphics;

/**
 *
 * @author eriks
 */
public class World {
    private Handler handler;
    private int width, height,spawnX,spawnY;
    private int [][] tiles;
    //Entities
    private EntityManager entityManager;
    
    //Item
    private ItemManager itemManager;
    
    public World(Handler handler,String path){
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 500, 500));
        if(path.contains("Entrance")){
            //Set Entities to set locations
            entityManager.addEntity(new Tree(handler, 400, 200)); //works
            
            entityManager.addEntity(new EntranceToMudroomDoor(handler,704,320));
            entityManager.addEntity(new EntranceToLivingRoomDoor(handler,448,192));
            //itemManager.addItem(Item.rockP);
        }
        if(path.contains("Mudroom")){
            entityManager.addEntity(new MudroomToEntranceDoor(handler,192,320));
        }
        if(path.contains("Livingroom")){
           entityManager.addEntity(new LivingRoomToEntranceDoor(handler,450,650));
           entityManager.addEntity(new LivingRoomToGarageDoor(handler,128,576));
           entityManager.addEntity(new LivingRoomToKitchenDoor(handler,384,192));      
        }
        if(path.contains("Garage")){
            entityManager.addEntity(new GarageToLivingRoomDoor(handler,896,660));
        }
        if(path.contains("Kitchen")){
            entityManager.addEntity(new KitchenToLivingRoomDoor(handler,256,480));
        }
        //entityManager.addEntity(new Rock(handler, 100, 500));
        //entityManager.addEntity(new Tree(handler, 300, 100));
        //entityManager.addEntity(new Rock(handler, 400, 200));
        
        itemManager = new ItemManager(handler);
        
        loadWorld(path);
        
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }
    
    public void tick(){
        itemManager.tick();
        entityManager.tick();
    }
    
    public void render(Graphics g){
        int xStart = (int)Math.max(0,(int)(handler.getGameCamera().getxOffset() / Tile.TILEWIDTH)); //Far left Tile
        int xEnd = (int)Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1); //Far right tile
        int yStart = (int)Math.max(0,(int)(handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT)); //Far Top tile
        int yEnd = (int)Math.min(height, (int) (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1); //Far Bottom tile
        
        for (int y = yStart;y<yEnd;y++){
            for(int x = xStart;x<xEnd;x++){
                getTile(x,y).render(g, (int)(x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int)(y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        //Items
        itemManager.render(g);
        
        //Entities
        entityManager.render(g);
    }
    
    public Tile getTile(int x, int y){
        if(x < 0|| y < 0|| x>= width|| y>=height)   
            return Tile.grassTile;
            
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
            return Tile.dirtTile;
        return t;
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    private void loadWorld(String path){
       //loading world from file using tile IDs 
        String file = Utils.loadFileAsString(path);
        
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        
        tiles = new int [width][height];
        for(int y = 0; y< height;y++){
            for(int x = 0;x< width;x++){
                tiles[x][y] = Utils.parseInt(tokens[(x + y *width) + 4]);
            }
        }       
    }
    
    public int getWidth(){
            return width;
        }
        
    public int getHeight(){
            return height;
        }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
    
}
