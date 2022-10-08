/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

/**
 *
 * @author eriks
 */
public class Assets {
    
    private static final int width = 32, height = 32;
    private static final int w =36, h = 29;
    public static Font defFont28;
    
    public static BufferedImage titleScreen;
    public static BufferedImage garage1,garage2,garage3;
    public static BufferedImage kitchen1,kitchen2;
    public static BufferedImage blackTile;
    public static BufferedImage grayChecker,blueCarpet,redCarpet,greenCarpet;
    public static BufferedImage uTdStairs,dTuStairs,lTrStairs,rTlStairs, horizontalStairs, verticalStairs;
    public static BufferedImage dirt, grass, stone, tree, rock;//
    public static BufferedImage firstAid, stoneP, rockP, stoneNrockP;//items 
    public static BufferedImage knifeIcon; //weapons
    public static BufferedImage[] player_LpickUp,player_RpickUp,player_UpickUp,player_DpickUp; //pickup
    public static BufferedImage[] player_Lidle,player_Ridle,player_Uidle,player_Didle; //idle
    public static BufferedImage[] player_down,player_up,player_right,player_left,player_idle;//movements
    public static BufferedImage[] player_run_down,player_run_up,player_run_right,player_run_left; //run
    public static BufferedImage[] player_kdown,player_kup,player_kright,player_kleft; //knife
    public static BufferedImage[] shadow_down,shadow_up,shadow_right,shadow_left; //enemy
    public static BufferedImage[] btn_mainGame,btn_customGame,btn_settings,btn_quit; //button
    public static BufferedImage[] add,sub;
    public static BufferedImage sound,brightness;
    public static BufferedImage inventoryScreen;
    public static BufferedImage[] fine,caution,hurt,danger;//Status Condition
    
    public static BufferedImage entranceToMudroomDoor,entranceToLivingRoomDoor,entranceToGarageDoor,entranceToKitchenDoor;
    public static BufferedImage mudroomToEntranceDoor,mudroomToLivingRoomDoor,mudroomToGarageDoor,mudroomToKitchenDoor;
    public static BufferedImage livingRoomToEntranceDoor,livingRoomToMudroomDoor,livingRoomToGarageDoor,livingRoomToKitchenDoor;
    public static BufferedImage garageToEntranceDoor,garageToMudroomDoor,garageToLivingRoomDoor,garageToKitchenDoor;
    public static BufferedImage kitchenToEntranceDoor,kitchenToMudroomDoor,kitchenToLivingRoomDoor,kitchenToGarageDoor;
    
    
    public static void init(){
        
        defFont28 = Font.getFont(Font.DIALOG);
        
        SpriteSheet settings = new SpriteSheet(ImageLoader.loadImage("/textures/SettingsIcons.png"));
        
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Sample1.png"));
        SpriteSheet player = new SpriteSheet(ImageLoader.loadImage("/textures/Thomas Fisher WALK.png"));
        SpriteSheet playerRun = new SpriteSheet(ImageLoader.loadImage("/textures/Thomas Fisher RUN.png"));
        SpriteSheet buttons = new SpriteSheet(ImageLoader.loadImage("/textures/buttons.png"));
        SpriteSheet rocks = new SpriteSheet(ImageLoader.loadImage("/textures/rock.png"));
        SpriteSheet knifeAttack = new SpriteSheet(ImageLoader.loadImage("/textures/Knife Thomas Fisher.png"));
        SpriteSheet items = new SpriteSheet(ImageLoader.loadImage("/textures/FirstAid.png"));
        SpriteSheet p = new SpriteSheet(ImageLoader.loadImage("/textures/stoneP&rockP.png"));
        SpriteSheet pickup = new SpriteSheet(ImageLoader.loadImage("/textures/ThomasFisherPickUp.png"));
        SpriteSheet estateFlooring = new SpriteSheet(ImageLoader.loadImage("/textures/FloorTile.png"));
        SpriteSheet charPortraits = new SpriteSheet(ImageLoader.loadImage("/textures/char portrait.png"));
        SpriteSheet door = new SpriteSheet(ImageLoader.loadImage("/textures/Door.png"));
        SpriteSheet tiles2 = new SpriteSheet(ImageLoader.loadImage("/textures/tiles2.png"));
        blackTile = ImageLoader.loadImage("/textures/Black Tile.png");
        titleScreen = ImageLoader.loadImage("/textures/titleScreen.png");
        
        //inventory
        inventoryScreen = ImageLoader.loadImage("/textures/inventory.png");
        
        fine = new BufferedImage[7];
        fine[0] = charPortraits.crop(0, 0, w, h);
        fine[1] = charPortraits.crop(w, 0, w, h);
        fine[2] = charPortraits.crop(w*2, 0, w, h);
        fine[3] = charPortraits.crop(w*3, 0, w, h);
        fine[4] = charPortraits.crop(0, h, w, h);
        fine[5] = charPortraits.crop(w, h, w, h);
        fine[6] = charPortraits.crop(w*2, h, w, h);
        
        
        blueCarpet = ImageLoader.loadImage("/textures/blueCarpet.png");
        btn_mainGame = new BufferedImage[2];
        btn_mainGame[0] = buttons.crop(0,0, 128, 128);
        btn_mainGame[1] = buttons.crop(128,0, 128, 128);
        
        btn_customGame = new BufferedImage[2];
        btn_customGame[0]= buttons.crop(0,128, 128, 128);
        btn_customGame[1]= buttons.crop(128,128, 128, 128);
        
        btn_settings = new BufferedImage[2];
        btn_settings[0] = buttons.crop(128, 128*2, 128, 128);
        btn_settings[1] = buttons.crop(0, 128*2, 128, 128);
        
        btn_quit = new BufferedImage[2];
        btn_quit[0] = buttons.crop(0, 128*3, 128, 128);
        btn_quit[1] = buttons.crop(128, 128*3, 128, 128);
        
        //settings
        add = new BufferedImage[2];
        sub = new BufferedImage[2];
        sub[0] = settings.crop(0, 0, width, height);
        sub[1] = settings.crop(width, 0, width, height);
        add[0] = settings.crop(0, height, width, height);
        add[1] = settings.crop(width, height, width, height);

        
        sound = settings.crop(0, width, width, height);
        brightness = settings.crop(width, width, width, height);
        
        //IDLES
        player_Lidle = new BufferedImage[1];
        player_Ridle = new BufferedImage[1];
        player_Uidle = new BufferedImage[1];
        player_Didle = new BufferedImage[1];
        //WALKS
        player_down = new BufferedImage[4];
        player_up = new BufferedImage[4];
        player_right = new BufferedImage[4];
        player_left = new BufferedImage[4];
        
        //RUNS
        player_run_down = new BufferedImage[4];
        player_run_up = new BufferedImage[4];
        player_run_right = new BufferedImage[4];
        player_run_left = new BufferedImage[4];
        
        //KNIFES
        player_kdown = new BufferedImage[2];
        player_kup = new BufferedImage[2];
        player_kright = new BufferedImage[2];
        player_kleft = new BufferedImage[2];
        
        //PickUp
        player_DpickUp = new BufferedImage[2];
        player_UpickUp = new BufferedImage[2];
        player_LpickUp = new BufferedImage[2];
        player_RpickUp = new BufferedImage[2];
        
        //IDLE
        player_Ridle[0] = player.crop(width, height*2, width, height);
        player_Lidle[0] = player.crop(width, height*3, width, height);
        player_Uidle[0] = player.crop(width, height, width, height);
        player_Didle[0] = player.crop(width, 0, width, height);
        
        //WALK
        player_down[0] = player.crop(width, 0, width, height); //neutral
        player_down[1] = player.crop(width*2, 0, width, height); // nright
        player_down[2] = player.crop(width, 0, width, height); //neutral
        player_down[3] = player.crop(0, 0, width, height); //nleft
        player_up[0] = player.crop(width, height, width, height); //neutral
        player_up[1] = player.crop(width*2, height, width, height); // nright
        player_up[2] = player.crop(width, height, width, height); //neutral
        player_up[3] = player.crop(0, height, width, height); //nleft
        player_right[0] = player.crop(width, height*2, width, height); //neutral
        player_right[1] = player.crop(width*2, height*2, width, height); // nright
        player_right[2] = player.crop(width, height*2, width, height); //neutral
        player_right[3] = player.crop(0, height*2, width, height); //nleft
        player_left[0] = player.crop(width, height*3, width, height); //neutral
        player_left[1] = player.crop(width*2, height*3, width, height); // nright
        player_left[2] = player.crop(width, height*3, width, height); //neutral
        player_left[3] = player.crop(0, height*3, width, height); //nleft
        
        //RUN
        player_run_down[0] = playerRun.crop(width, 0, width, height);
        player_run_down[1] = playerRun.crop(width*2, 0, width, height);
        player_run_down[2] = playerRun.crop(width, 0, width, height);
        player_run_down[3] = playerRun.crop(0, 0, width, height);
        player_run_up[0] = playerRun.crop(width, height, width, height);
        player_run_up[1] = playerRun.crop(width*2, height, width, height);
        player_run_up[2] = playerRun.crop(width, height, width, height);
        player_run_up[3] = playerRun.crop(0, height, width, height);
        player_run_right[0] = playerRun.crop(width, height*2, width, height);
        player_run_right[1] = playerRun.crop(width*2, height*2, width, height);
        player_run_right[2] = playerRun.crop(width, height*2, width, height);
        player_run_right[3] = playerRun.crop(0, height*2, width, height);
        player_run_left[0] = playerRun.crop(width, height*3, width, height);
        player_run_left[1] = playerRun.crop(width*2, height*3, width, height);
        player_run_left[2] = playerRun.crop(width, height*3, width, height);
        player_run_left[3] = playerRun.crop(0, height*3, width, height);
        
        //Knife
        player_kdown[0] = knifeAttack.crop(width, 0, width, height); //neutral
        player_kdown[1] = knifeAttack.crop(0, 0, width, height); // nright
        player_kup[0] = knifeAttack.crop(width, height, width, height); //neutral
        player_kup[1] = knifeAttack.crop(0, height, width, height); // nright
        player_kright[0] = knifeAttack.crop(width, height*2, width, height); //neutral
        player_kright[1] = knifeAttack.crop(0, height*2, width, height); // nright
        player_kleft[0] = knifeAttack.crop(width, height*3, width, height); //neutral
        player_kleft[1] = knifeAttack.crop(0, height*3, width, height); // nright
        
        player_DpickUp[0] = pickup.crop(width, 0, width, height); //neutral
        player_DpickUp[1] = pickup.crop(width, 0, width, height); // nright
        player_UpickUp[0] = pickup.crop(width, height, width, height); //neutral
        player_UpickUp[1] = pickup.crop(width, height, width, height); // nright
        player_RpickUp[0] = pickup.crop(width, height*2, width, height); //neutral
        player_RpickUp[1] = pickup.crop(width, height*2, width, height); // nright
        player_LpickUp[0] = pickup.crop(width, height*3, width, height); //neutral
        player_LpickUp[1] = pickup.crop(width, height*3, width, height); // nright
        
        
        //TILES
        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width*2, 0, width, height);
        stone = sheet.crop(width*3, 0, width, height);
        
        //Entities
        tree = sheet.crop(0, height, width, height);
        rock = rocks.crop(0, 0, width, height);
        
        //ENTITY DOORS MAIN GAME
        entranceToMudroomDoor = door.crop(0, 0, width, height);
        entranceToLivingRoomDoor = door.crop(width, 0, width, height);
        mudroomToEntranceDoor = door.crop(width*2, 0, width, height);
        livingRoomToEntranceDoor = door.crop(width*3, 0, width, height);
        livingRoomToGarageDoor = door.crop(width*4, 0, width, height);
        livingRoomToKitchenDoor = door.crop(width*5, 0, width, height);
        garageToLivingRoomDoor = door.crop(width*6, 0, width, height);
        kitchenToLivingRoomDoor = door.crop(0, height, width, height);
        
        //CUSTOM GAME DOORS
        entranceToGarageDoor = door.crop(width, height, width, height);
        entranceToKitchenDoor = door.crop(width*2, height, width, height);
        mudroomToLivingRoomDoor = door.crop(width*3, height, width, height);
        mudroomToGarageDoor = door.crop(width*4, height, width, height);
        mudroomToKitchenDoor = door.crop(width*5, height, width, height);
        livingRoomToMudroomDoor = door.crop(width*6, height, width, height);
        garageToEntranceDoor = door.crop(width, height*2, width, height);
        garageToMudroomDoor = door.crop(width*2, height*2, width, height);
        garageToKitchenDoor = door.crop(width*3, height*2, width, height);
        kitchenToEntranceDoor = door.crop(width*4, height*2, width, height);
        kitchenToMudroomDoor = door.crop(width*5, height*2, width, height);
        kitchenToGarageDoor = door.crop(width*6, height*2, width, height);

        
        
        uTdStairs = estateFlooring.crop(width*4, 0, width, height);
        dTuStairs = estateFlooring.crop(width*2, 0, width, height);
        lTrStairs = estateFlooring.crop(width*8, 0, width, height);
        rTlStairs = estateFlooring.crop(width*6, 0, width, height);
        horizontalStairs = estateFlooring.crop(width*3, 0, width, height);
        verticalStairs = estateFlooring.crop(width*7, 0, width, height);
        
        //garage
        garage1 = tiles2.crop(0, 0, width, height);
        garage2 = tiles2.crop(width, 0, width, height);
        garage3 = tiles2.crop(0, height, width, height);
        
        //kitchen
        kitchen1 = tiles2.crop(width, height, width, height);
        kitchen2 = tiles2.crop(0, height*2, width, height);
        
        //blueCarpet
        redCarpet = estateFlooring.crop(width, 0, width, height);
        greenCarpet = estateFlooring.crop(width*11, 0, width, height);
        
        grayChecker = estateFlooring.crop(0, 0, width, height);
        
        //Big Weapons Slots
        knifeIcon = ImageLoader.loadImage("/textures/KNIFEICON.png");
        
        //items
        firstAid = items.crop(0, 0, width, height);
        stoneP = p.crop(0, 0, width, height);
        rockP = p.crop(width, 0, width, height);
        stoneNrockP = p.crop(width*2, 0, width, height);
    }
    
}
