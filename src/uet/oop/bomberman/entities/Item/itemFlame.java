package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.map.ConvertCordinate;
import uet.oop.bomberman.entities.MovingEntities.Bomber;
import uet.oop.bomberman.graphics.Sprite;


public class itemFlame extends Item {
    private boolean isTaken = false;
    private boolean isReveal = false;
    Bomber bomber;
    private int CountDownTime = 2000;
    public itemFlame(int x, int y, Image img){
        super(x,y,img);
        isTaken=false;
        isReveal=false;
    }

    @Override
    public void setImg(Image img) {
        super.setImg(img);
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public void setRevealed(boolean reveal) {
        BombermanGame.MovableMap[ConvertCordinate.getTileY(y)][ConvertCordinate.getTileX(x)]=1;
        isReveal = reveal;
    }

    @Override
    public void addToBomber(Bomber bomber) {
        isTaken=true;
        this.bomber=bomber;
        BombermanGame.itemMap[ConvertCordinate.getTileY(y)][ConvertCordinate.getTileX(x)]=' ';
        //them 1 bomb
    }
    public void countdown(){
        if(isTaken==true&&CountDownTime>0){
            CountDownTime--;
        }
        if(CountDownTime==0){
            isTaken=false;
            bomber.setBOMBLENGTHDEFAULT(1);
            remove();
        }
    }
    public void remove(){
        BombermanGame.removeEntities(this);
    }
    @Override
    public void update() {
        if(isReveal){
            setImg(Sprite.powerup_flames.getFxImage());
        }
        if(isTaken){
            setImg(Sprite.grass.getFxImage());
            bomber.setBOMBLENGTHDEFAULT(2);
        }
        countdown();
    }


}
