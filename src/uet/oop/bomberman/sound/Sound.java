package uet.oop.bomberman.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    Clip clip;
    String soundURL;

    public Sound(){

        soundURL=("res/sound/BlueBoyAdventure.wav");
    }
    public void setFile(){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(soundURL).getAbsoluteFile());
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
