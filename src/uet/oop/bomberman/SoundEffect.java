package uet.oop.bomberman;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundEffect {
        Clip clip;
        String[] soundURL = new String[30];

        public SoundEffect(){
            //Bomb explode
            soundURL[0]=("res/sound/burning.wav");
            //Kill enemy
            soundURL[1]=("res/sound/coin.wav");
            //Get item
            soundURL[2]=("res/sound/powerup.wav");
            //PlantBomb
            soundURL[3]=("res/sound/blocked.wav");
            //Game Over
            soundURL[4]=("res/sound/gameover.wav");
            //Walk
            soundURL[5]=("res/sound/cursor.wav");
            //clear stage
            soundURL[6]=("res/sound/fanfare.wav");
//            soundURL[7]=("res/sound/Stage Intro.wav");
//            soundURL[8]=("res/sound/Time Up (Full).wav");
        }
        public void setFile(int i){
            try{
                AudioInputStream ais = AudioSystem.getAudioInputStream(new File(soundURL[i]).getAbsoluteFile());
                clip=AudioSystem.getClip();
                clip.open(ais);
            }catch (Exception e){
            }
        }
        public void loop(){
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        public void play(){
            clip.start();
        }
        public void stop(){
            clip.stop();
        }
    }
