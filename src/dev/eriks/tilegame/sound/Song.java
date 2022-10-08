/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.sound;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author eriks
 */
public class Song {
    private File file;
    private String fileName;
    private long length;
    public Song(File file, String fileName){
        this.file = file;
        this.fileName = fileName;
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            length = clip.getMicrosecondLength();
        }
        catch(Exception e){
            
        }
    }
    
    public String getFileName(){
        return fileName;
    }
    public long getlengthOfSongInMillis(){
        return length;
    }

    public File getFile() {
        return file;
    }
    
}
