package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.ConvertCordinate;
import uet.oop.bomberman.entities.Item.Item;
import uet.oop.bomberman.entities.MovingEntities.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class itemSpeed extends Item {
    private boolean isTaken;
    private boolean isRevealed;
    private int CountDownTime=1500;
    Bomber bomber;
    public itemSpeed(int x, int y, Image img) {
        super(x, y, img);
        isTaken=false;
        isRevealed=false;
    }

    @Override
    public void setImg(Image img) {
        super.setImg(img);
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
        BombermanGame.MovableMap[ConvertCordinate.getTileY(y)][ConvertCordinate.getTileX(x)]=1;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    @Override
    public void addToBomber(Bomber bomber) {
        isTaken=true;
        this.bomber=bomber;
        BombermanGame.itemMap[ConvertCordinate.getTileY(y)][ConvertCordinate.getTileX(x)]=' ';
    }

    public void remove(){
        BombermanGame.removeEntities(this);
    }

    public void countdown(){
        if(isTaken==true&&CountDownTime>0){
            CountDownTime--;
        }
        if(CountDownTime==0){
            isTaken=false;
            bomber.setSpeed(1);
            remove();
        }
    }

    @Override
    public void update() {
        if(isRevealed){
            setImg(Sprite.powerup_speed.getFxImage());
        }
        if(isTaken) {
            if (bomber.isAlive) {
                setImg(Sprite.grass.getFxImage());
                bomber.setSpeed(3);
            }
        }
        countdown();
    }
}
