/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.worlds;

import java.util.Random;

/**
 *
 * @author eriks
 */
public class TheWorlds {
    
    private String entrance,mudroom,livingroom,kitchen,garage;
    public TheWorlds(){
        entrance = "res/worlds/Entrance";
        mudroom = "res/worlds/Mudroom";
        livingroom = "res/worlds/Livingroom";
        kitchen = "res/worlds/Kitchen";
        garage = "res/worlds/Garage";
    }

    public String getEntrance() {
        return entrance;
    }
    public String getMudroom() {
        return mudroom;
    }
    public String getLivingroom() {
        return livingroom;
    }

    public String getKitchen() {
        return kitchen;
    }

    public String getGarage() {
        return garage;
    }
    public String getRandomStartRoom(){
        Random r = new Random();
        int raffle = r.nextInt(4);
        System.out.println("raffle = " + raffle);
        String s = null;
        switch (raffle) {
            case 0:
                s = getMudroom();
                break;
            case 1:
                s = getLivingroom();
                break;
            case 2:
                s = getGarage();
                break;
            case 3:
                s = getKitchen();
                break;
        }
        return s;
    }
    
    
    
}
