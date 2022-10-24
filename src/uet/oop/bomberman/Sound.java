package uet.oop.bomberman;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound(){
        soundURL[0]=getClass().getResource("res/sound/Walking 1.wav");
        soundURL[1]=getClass().getResource("res/sound/Place Bomb.wav");
        soundURL[2]=getClass().getResource("res/sound/Enemy Dies.wav");
        soundURL[3]=getClass().getResource("res/sound/Item Get.wav");
        soundURL[4]=getClass().getResource("res/sound/Bomb Explodes.wav");
        soundURL[5]=getClass().getResource("res/sound/Bomberman Dies.wav");
        soundURL[6]=getClass().getResource("res/sound/Stage Clear.wav");
        soundURL[7]=getClass().getResource("res/sound/Stage Intro.wav");
        soundURL[8]=getClass().getResource("res/sound/Time Up (Full).wav");
        soundURL[9]=getClass().getResource("sound/Stage Theme.wav");
    }
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip=AudioSystem.getClip();
            clip.open(ais);
        }catch (Exception e){
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
