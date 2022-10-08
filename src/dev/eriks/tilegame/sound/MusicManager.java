/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.sound;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author eriks
 */
public class MusicManager {
    public String title,mainhall;
    public File titleFile,mainhallFile;
    public Song titleSong,mainhallSong;
    public Music m;
    public MusicManager() throws IOException, FileNotFoundException, InterruptedException, LineUnavailableException, UnsupportedAudioFileException{
        title = "res\\\\music\\\\Balcony Theme.wav";
        titleFile = new File(title);
        titleSong = new Song(titleFile,title);
        
        mainhall = "res\\\\music\\\\saferoom.wav";
        mainhallFile = new File(mainhall);
        mainhallSong = new Song(mainhallFile,mainhall);
        
        
    }

    public void playMenu() throws IOException, FileNotFoundException, InterruptedException{
        m = new Music(titleSong);
    }
    public void playMainHall() throws IOException, FileNotFoundException, InterruptedException{
        m = new Music(mainhallSong);
    }
    public void stopMusic(){
        m.stopMusic();
    }
    public String getTitle() {
        return title;
    }

    public File getTitleFile() {
        return titleFile;
    }
    
}
