/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import static java.nio.file.Files.size;

/**
 *
 * @author eriks
 */
public class FontLoader {
    
    public static Font loadFont(String path, float size){
        try{
        return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN,size);
        }
        catch(FontFormatException | IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
    
}
