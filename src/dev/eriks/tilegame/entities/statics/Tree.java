/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.entities.statics;

import dev.eriks.tilegame.Handler;
import dev.eriks.tilegame.gfx.Assets;
import dev.eriks.tilegame.items.Item;
import dev.eriks.tilegame.tiles.Tile;
import java.awt.Graphics;

/**
 *
 * @author eriks
 */
public class Tree extends StaticEntity {

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2);

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
        g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.stoneP.createNew((int) x, (int) y));
    }

}
