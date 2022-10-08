/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.entities.statics;

import dev.eriks.tilegame.Handler;
import dev.eriks.tilegame.gfx.Assets;
import dev.eriks.tilegame.tiles.Tile;
import dev.eriks.tilegame.worlds.TheWorlds;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eriks
 */
public class LivingRoomToEntranceDoor extends StaticEntity {

    public TheWorlds worlds = new TheWorlds();

    public LivingRoomToEntranceDoor(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        bounds.x = 20;
        bounds.y = (int) (height / 1.5f);
        bounds.width = width - 40;
        bounds.height = (int) (height - height / 1.5f);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.livingRoomToEntranceDoor, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {
        //worlds.getMudroom();
        handler.getGameState().setTheWorldPath(worlds.getEntrance());
        try {
            handler.getGameState().changeWorld();
            //handler.getWorld().getItemManager().addItem(Item.rockP.createNew((int)x, (int)y));
        } catch (InterruptedException ex) {
            Logger.getLogger(Rock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
