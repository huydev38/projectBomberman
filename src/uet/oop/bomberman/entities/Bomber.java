package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.ConvertCordinate;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends AnimatedObject {

    private int bombCount=1;
    private int speed=1;
    private int heart=3;
    private boolean moveUP;
    private boolean moveDOWN;
    private boolean moveRIGHT;
    private boolean moveLEFT;
    private char[][] mapMatrix;


    public Bomber(int x, int y, Image img, char[][]mapMatrix) {
        super( x, y, img);
        this.mapMatrix=mapMatrix;
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
            if (canMove(mapMatrix, x-speed, y+2,x-speed, y+26)) {
                x -= speed;
                setImg((Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, getX(), 15)).getImage());
            }
        }
        if(moveRIGHT) {
            if(canMove(mapMatrix,x+speed+26,y+2, x+speed+26, y+26)) {
                x += speed;
                setImg((Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, getX(), 15)).getImage());
            }
        }
        if(moveUP) {
            if(canMove(mapMatrix,x+2,y-speed,x+26,y-speed)) {
                y -= speed;
                setImg((Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, getY(), 15)).getImage());
            }
        }
        if(moveDOWN){
            if(canMove(mapMatrix,x+2,y+speed+25,x+26,y+speed+26)) {
                y += speed;
                setImg((Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, getY(), 15)).getImage());
            }
        }
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
    public boolean canMove(char[][] mapMatrix, int xm, int ym, int xn, int yn) {
        xm= ConvertCordinate.PixelToTile(xm);
        ym= ConvertCordinate.PixelToTile(ym);
        xn=ConvertCordinate.PixelToTile(xn);
        yn=ConvertCordinate.PixelToTile(yn);
        if(mapMatrix[ym][xm]=='*'||mapMatrix[ym][xm]=='#'||mapMatrix[yn][xn]=='*'||mapMatrix[yn][xn]=='#'){
            return false;
        }
        return true;
    }

    @Override
    public void update() {
        moving();
    }
}
