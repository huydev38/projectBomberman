package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.ConvertCordinate;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends AnimatedObject {

    private int bombCount = 1;
    private int speed = 1;
    private int heart = 3;
    private boolean moveUP;
    private boolean moveDOWN;
    private boolean moveRIGHT;
    private boolean moveLEFT;
    private boolean plantBomb;


    private boolean isMove = false;


    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void setImg(Image img) {
        super.setImg(img);
    }

    public int getBombCount() {
        return this.bombCount;
    }

    public int getX() {
        return x;
    }

    public int getY() {
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

    public void moving() {
        int tempX=x;
        int tempY=y;
        if (moveLEFT) {
            if (canMove(x, y, "LEFT", this.speed)) {
                //if (canMove(mapMatrix, x-speed, y+2,x-speed, y+26)) {
                x -= speed;
                isMove=true;
                updateTile(tempX, tempY);
                setImg((Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, getX(), 15)).getImage());
            }
        }
        if (moveRIGHT) {
            if (canMove(x, y, "RIGHT", this.speed)) {
                //if(canMove(mapMatrix,x+speed+26,y+2, x+speed+26, y+26)) {
                x += speed;
                updateTile(tempX, tempY);
                setImg((Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, getX(), 15)).getImage());
            }
        }
        if (moveUP) {
            if (canMove(x, y, "UP",this.speed)) {
                //if(canMove(mapMatrix,x+2,y-speed,x+26,y-speed)) {
                y -= speed;
                updateTile(tempX, tempY);
                setImg((Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, getY(), 15)).getImage());
            }
        }
        if (moveDOWN) {
            if (canMove(x, y, "DOWN",this.speed)) {
                //if(canMove(mapMatrix,x+2,y+speed+25,x+26,y+speed+26)) {
                y += speed;
                updateTile(tempX, tempY);
                setImg((Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, getY(), 15)).getImage());
            }
        }
    }

    public void handleKeyPress(KeyCode k) {
        switch (k) {
            case W:
                moveUP = true;
                break;
            case A:
                moveLEFT = true;
                break;
            case S:
                moveDOWN = true;
                break;
            case D:
                moveRIGHT = true;
                break;
            case SPACE:
                plantBomb = true;
                break;
        }
    }

    public void handleKeyReleased(KeyCode k) {
        switch (k) {
            case W:
                moveUP = false;
                break;
            case A:
                moveLEFT = false;
                break;
            case S:
                moveDOWN = false;
                break;
            case D:
                moveRIGHT = false;
                break;
            case SPACE:
                plantBomb=false;
                break;
        }
    }

    //Kiem tra xem co dat bomb chua
    public void detectPlantBomb(){
        if(plantBomb &&bombCount>0){
            Bomb b = new Bomb(ConvertCordinate.getTileX(x),ConvertCordinate.getTileY(y),Sprite.bomb_2.getImage(), this);
            BombermanGame.addEntities(b);
            b.updateTile(ConvertCordinate.getTileX(x),ConvertCordinate.getTileY(y), this);
            setBombCount(getBombCount()-1);
        }
    }
    @Override
    public void update() {
        moving();
        detectPlantBomb();
    }
    //update vị trí trên map
    @Override
    public void updateTile(int tx, int ty) {
         //  BombermanGame.mapMatrix[ConvertCordinate.getTileY(ty)][ConvertCordinate.getTileX(tx)]=' ';
         //  BombermanGame.mapMatrix[ConvertCordinate.getTileY(y)][ConvertCordinate.getTileX(x)]='p';
    }

    public void checkCollide(){
        if(up)
    }
}
