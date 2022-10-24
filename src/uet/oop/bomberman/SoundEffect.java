package uet.oop.bomberman;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundEffect {
        Clip clip;
        String[] soundURL = new String[30];

        public SoundEffect(){
            soundURL[0]=("res/sound/Walking 1.wav");
            soundURL[1]=("res/sound/Place Bomb.wav");
            soundURL[2]=("res/sound/Enemy Dies.wav");
            soundURL[3]=("res/sound/Item Get.wav");
            soundURL[4]=("res/sound/Bomb Explodes.wav");
            soundURL[5]=("res/sound/Bomberman Dies.wav");
            soundURL[6]=("res/sound/Stage Clear.wav");
            soundURL[7]=("res/sound/Stage Intro.wav");
            soundURL[8]=("res/sound/Time Up (Full).wav");
        }
        public void setFile(int i){
            try{
                AudioInputStream ais = AudioSystem.getAudioInputStream(new File(soundURL[i]).getAbsoluteFile());
                clip=AudioSystem.getClip();
                clip.open(ais);
            }catch (Exception e){
            }
        }
        public void play(){
            clip.start();
        }
        public void stop(){
            clip.stop();
        }
    }
