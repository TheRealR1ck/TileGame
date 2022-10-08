/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.tiles;

import dev.eriks.tilegame.gfx.Assets;
import java.awt.image.BufferedImage;

/**
 *
 * @author eriks
 */
public class RockTile extends Tile{
    
    public RockTile(int id) {
        super(Assets.stone, id);
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
    
}
