package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private KeyCode direction;
    private int bombCount=1;
    private int speed=1;
    private int heart=3;
    private boolean moveUP;
    private boolean moveDOWN;
    private boolean moveRIGHT;
    private boolean moveLEFT;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void setImg(Image img) {
        super.setImg(img);
    }

    public int getBombCount() {
        return bombCount;
    }

    public void setBombCount(int bombCount) {
        this.bombCount = bombCount;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHeart() {
        return heart;
    }

    public int getSpeed() {
        return speed;
    }
    public void moving(){
        if(moveLEFT) {
            x -= speed;
        }
        if(moveRIGHT) x+=speed;
        if(moveUP) y-=speed;
        if(moveDOWN) y+=speed;
    }
    public void handleKeyPress(KeyCode k){

        switch(k){
            case W:
                moveUP=true;
                break;
            case A:
                moveLEFT=true;
                break;
            case S:
                moveDOWN=true;
                break;
            case D:
                moveRIGHT=true;
                break;
        }
    }
    public void handleKeyReleased(KeyCode k){
        switch(k){
            case W:
                moveUP=false;
                y+=speed;
                break;
            case A:
                moveLEFT=false;
                x-=speed;
                break;
            case S:
                moveDOWN=false;
                y-=speed;
                break;
            case D:
                moveRIGHT=false;
                x+=speed;
                break;
        }
    }
    @Override
    public void update() {
        moving();
    }
}
