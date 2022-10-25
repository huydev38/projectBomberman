package uet.oop.bomberman.entities.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.ConvertCordinate;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntities.Bomber;
import uet.oop.bomberman.graphics.Sprite;


public class Bomb extends Entity {
    private boolean isExploded = false;
    private int countDownTime = 90*3;
    Bomber bomber;
    private int LengthDefault;

    public Bomb(int x, int y, Image img, Bomber bomber) {
        super(x, y, img);
        this.bomber = bomber;
        this.LengthDefault=bomber.getBOMBLENGTHDEFAULT();
    }

    public void countDown() {
        if(countDownTime!=0) {
            countDownTime--;
        }
        if (countDownTime == 90*3) {
            setImg(Sprite.bomb_2.getImage());
        }
        if (countDownTime == 60*3) {
            setImg(Sprite.bomb_1.getImage());
        }
        if (countDownTime == 30*3) {
            setImg(Sprite.bomb.getImage());
        }
    }

    public void setCountDownTime(int countDownTime) {
        this.countDownTime = countDownTime;
    }

    @Override
    public void update() {
        countDown();
        setExploded();
    }

    public void remove() {
        BombermanGame.removeEntities(this);
    }

    public boolean checkExploded() {
        if (countDownTime == 0) {
            BombermanGame.playSE(0);
            isExploded = true;
        }
        return isExploded;
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

    @Override
    public void setImg(Image img) {
        super.setImg(img);
    }

    public void setExploded() {
        if (checkExploded()) {

            BombSegment bombSegment = new BombSegment(ConvertCordinate.getTileX(x),ConvertCordinate.getTileY(y),Sprite.bomb_exploded.getImage(),LengthDefault);
            BombermanGame.addEntities(bombSegment);
            bomber.setBombCount(bomber.getBombCount() -1);
            BombermanGame.bombCount+=1;
            remove();
        }
        updateTile(ConvertCordinate.getTileX(x), ConvertCordinate.getTileY(y),this.bomber);

    }

    public void updateTile(int tx, int ty, Bomber bomber1) {
        if (isExploded == false && (ConvertCordinate.getTileX(bomber1.getX())!=tx||ConvertCordinate.getTileY(bomber1.getY())!=ty)) {
            BombermanGame.MovableMap[ty][tx] = 0;
        } else {
            BombermanGame.MovableMap[ty][tx]=1;
        }
    }
}
