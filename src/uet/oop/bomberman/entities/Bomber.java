package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends AnimatedObject {

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
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
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
        setImg((Sprite.movingSprite(Sprite.player_left,Sprite.player_left_1,Sprite.player_left_2,getX(),15)).getImage());
        }
        if(moveRIGHT) {
            x += speed;
            setImg((Sprite.movingSprite(Sprite.player_right,Sprite.player_right_1,Sprite.player_right_2,getX(),15)).getImage());

        }
        if(moveUP) {
            y -= speed;
            setImg((Sprite.movingSprite(Sprite.player_up,Sprite.player_up_1,Sprite.player_up_2,getY(),15)).getImage());

        }
        if(moveDOWN){
            y+=speed;
            setImg((Sprite.movingSprite(Sprite.player_down,Sprite.player_down_1,Sprite.player_down_2,getY(),15)).getImage());

        }
    }
    public void handleKeyPress(KeyCode k,char [][]mapMatrix){

        switch(k){
            case W:
                if(canMove(mapMatrix,x,y)){
                    moveUP=true;
                }
                break;
            case A:
                if(canMove(mapMatrix,x,y)){
                    moveLEFT=true;
                }
                break;
            case S:
                if(canMove(mapMatrix,x,y)){
                    moveDOWN=true;
                }
                break;
            case D:
                if(canMove(mapMatrix,x,y)){
                    moveRIGHT=true;
                }
                break;
            default:
                moveDOWN=false;
                moveRIGHT=false;
                moveUP=false;
                moveLEFT=false;
        }
    }
    public void handleKeyReleased(KeyCode k){
        switch(k){
            case W:
                moveUP=false;
                break;
            case A:
                moveLEFT=false;
                break;
            case S:
                moveDOWN=false;
                break;
            case D:
                moveRIGHT=false;
                break;
        }
    }

    @Override
    public boolean canMove(char[][] mapMatrix, int xm, int ym) {
        return false;
    }

    @Override
    public void update() {
        moving();
    }
}
