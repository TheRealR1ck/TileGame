/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.tiles;

import dev.eriks.tilegame.gfx.Assets;

/**
 *
 * @author eriks
 */
public class BlackTile extends Tile {

    public BlackTile(int id) {
        super(Assets.blackTile, id);
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
    
}
