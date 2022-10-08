package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.ConvertCordinate;
import uet.oop.bomberman.graphics.Sprite;


public class Bomb extends Entity {
    private boolean isExploded = false;
    private int countDownTime = 180*3;
    private char [][] mapMatrix;
    Bomber bomber;

    public Bomb(int x, int y, Image img, Bomber bomber, char [][] mapMatrix) {
        super(x, y, img);
        this.bomber = bomber;
        this.mapMatrix=mapMatrix;
    }

    public void countDown() {
        if(countDownTime!=0) {
            countDownTime--;
        }
        if (countDownTime == 180*3) {
            setImg(Sprite.bomb_2.getImage());
        }
        if (countDownTime == 120*3) {
            setImg(Sprite.bomb_1.getImage());
        }
        if (countDownTime == 60*3) {
            setImg(Sprite.bomb.getImage());
        }
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
            //TODO: Create bomb segment
            remove();
            bomber.setBombCount(bomber.getBombCount() + 1);
            updateTile(mapMatrix, ConvertCordinate.getTileX(x), ConvertCordinate.getTileY(y));
            isExploded=false;
        }
    }

    public void updateTile(char[][] mapMatrix, int tx, int ty) {
        if (isExploded == false) {
            mapMatrix[ty][tx] = 'o';
        } else {
            mapMatrix[ty][tx]=' ';
        }
    }
}
