/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.inventory;

import com.sun.glass.events.KeyEvent;
import dev.eriks.tilegame.Handler;
import dev.eriks.tilegame.gfx.Animation;
import dev.eriks.tilegame.gfx.Assets;
import dev.eriks.tilegame.gfx.Text;
import dev.eriks.tilegame.items.Item;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author eriks
 */
public class Inventory {
    //private ArrayList<Slot> slots;
    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;
    
    private int invX = 0, invY = 0,
            invWidth = 800, invHeight = 600,
            invListCenterX = invX + 95,
            invListCenterY = invY + invHeight / 2 + 5;
            Animation green;
    
    //Slot coordinates
    private int slot1x =80 , slot1y = 215;
    private int slot2x =200 , slot2y = 215;
    private int slot3x =320 , slot3y = 215;
    private int slot4x =440 , slot4y = 215;
    //weapons
    private int sloteqx = 540 , sloteqy = 94;
    
    private int slote1x = 555, slote1y = 235;
    private int slote2x = 200 , slote2y = 215;
    //next row
    //private int slot5x =200 , slot5y = 215;
    
    public Inventory(Handler handler){
        this.handler = handler;
        //slots = new ArrayList<Slot>();
        inventoryItems = new ArrayList<Item>();
        green = new Animation(100,Assets.fine);
        addItem(Item.rockP);
        addItem(Item.stoneP);
        addItem(Item.firstAid);
        addItem(Item.stoneNrockP);
    }
    
    public void tick(){
        green.tick();
        
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
            active = !active;
        if(!active)
            return;
        
        System.out.println("Inventory:");
        for(Item i : inventoryItems){
            System.out.println(i.getName() + "  " + i.getCount());
        }
    }
    
    private int getSlotCoordinateX(int i){
        if(i == 0){
            System.out.println("i = 0 rn\nIndex:" + i);
            return slot1x;
        }else if(i == 1){
            System.out.println("i = 1 rn\nIndex:" + i);
            return slot2x;
        }
        else if(i == 2){
            System.out.println("i = 2 rn\nIndex:" + i);
            return slot3x;
        }
        else if(i == 3){
            System.out.println("i = 3 rn\nIndex:" + i);
            return slot4x;
        }
        return 0;
    }
    
    private int getSlotCoordinateY(int i){
        if(i == 0){
            return slot1y;
        }else if(i == 1){
            return slot2y;
        }
        else if(i == 2){
            return slot3y;
        }
        else if(i == 3){
            return slot4y;
        }
        return 0;
    }
    
    private void checkItems(Graphics g) {
        int x = 0;
        if (!inventoryItems.isEmpty()) {
            for (Item i : inventoryItems) {
                switch (i.getId()) {
                    case 0:
                        //g.drawImage(Assets.firstAid.getScaledInstance(96, 96, 0), slot2x, slot2y, null);
                        g.drawImage(Assets.firstAid.getScaledInstance(96, 96, 0), getSlotCoordinateX(x), getSlotCoordinateY(x), null);
                        x++;
                        break;
                    case 1:
                        System.out.println("x = "+x);
                        g.drawImage(Assets.rockP.getScaledInstance(96, 96, 2), getSlotCoordinateX(x), getSlotCoordinateY(x), null);
                        x++;
                        break;
                    case 2:
                        g.drawImage(Assets.stoneP.getScaledInstance(96, 96, 2), getSlotCoordinateX(x), getSlotCoordinateY(x), null);
                        x++;
                        break;
                    case 3:
                        g.drawImage(Assets.stoneNrockP.getScaledInstance(96, 96, 2), getSlotCoordinateX(x), getSlotCoordinateY(x), null);
                        x++;
                        break;
                    default:
                        x++;
                        break;
                }
                
            }
        }
    }

    public void render(Graphics g){
        if(!active)
            return;

        
        
        //draw items
        
        //draw equipped
        
        g.drawImage(Assets.inventoryScreen, invX, invY,invWidth , invHeight,null);
        g.drawImage(green.getCurrentFrame(),74, 66, 225, 135, null);
        //check and draw status of character
        
        
        g.drawImage(Assets.knifeIcon, sloteqx, sloteqy, 28*5, 16*5, null);
        //equip
        
        //weapon item
        g.drawImage(Assets.knifeIcon, 555, 235, 28*5 - 2, 16*5 - 2, null);
        
        //check current Items list function then draw on screen
        
        checkItems(g);
        
        //g.drawImage(Assets.rockP.getScaledInstance(96, 96, 2), slot1x, slot1y, null);
        //g.drawImage(Assets.firstAid.getScaledInstance(96, 96, 0), slot2x, slot2y, null);
        Text.drawString(g, inventoryItems.get(1).getName(), invListCenterX, invListCenterY, false, Color.WHITE, Assets.defFont28);
        
    }

    //Inventory Methods
    
    public void addItem(Item item) {
        for (Item i : inventoryItems) {
            if (i.getId() == item.getId()) {
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }
    
    //Getters & setters
    
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    /*
    private static class Slot {
        int x, y;
        boolean selectable;
        Item item;
        protected Rectangle bounds;
        public Slot(Item item, boolean selectable, int x, int y) {
            this.item = item;
            this.selectable = selectable;
            this.x = x;
            this.y = y;
            
            bounds = new Rectangle();
        }
        
        public void addSlot(Slot slot) {
        for (Slot s : slots) {
            if (s.getSlotId() == slot.getSlotId()) {
                s.setCount(s.getSlotCount() + slot.getSlotCount());
                return;
            }
        }
        slots.add(slot);
    }
        
        private void createSelectableButton(){
            
        }

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

        public boolean isSelectable() {
            return selectable;
        }

        public void setSelectable(boolean selectable) {
            this.selectable = selectable;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }
        
        */
    }
    
    
    
//}
