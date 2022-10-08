/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.eriks.tilegame.sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 *
 * @author eriks
 */
public class Music {
    InputStream music;
    AudioStream audios;
    public Music(){
        
    }
    public Music(Song song)throws IOException, FileNotFoundException, InterruptedException{
        playMusic(song);
    }

    public void tick(){
        //if(audios.getData())
    }
    
    private void playMusic(Song song) throws FileNotFoundException, IOException, InterruptedException {
       /*
        try{
            AudioData data = new AudioStream (new FileInputStream(song.getFileName())).getData();
            ContinuousAudioDataStream sound = new ContinuousAudioDataStream(data);
            AudioPlayer.player.start(sound);
        }catch(Exception e){
            System.out.println("error");
        }
        */
        
        
        music = new FileInputStream(new File(song.getFileName()));
        audios = new AudioStream(music);
        
        AudioPlayer.player.start(audios);
        
        audios.reset();
        
        //AudioPlayer.sleep(song.getlengthOfSongInMillis());
        
       
       
    }
    public void stopMusic(){
        AudioPlayer.player.stop(audios);
    }
}
