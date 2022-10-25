package uet.oop.bomberman.entities.MovingEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.ConvertCordinate;
import uet.oop.bomberman.graphics.Sprite;

public class Minvo extends AnimatedObject {
    private int speed = 1;
    private boolean isAlive = true;
    private int dieTime = 180;
    String[] directions = {"LEFT", "DOWN", "LEFT", "DOWN", "UP", "RIGHT", "UP", "RIGHT"};
    String currentMove = "UP";
    int i = 0;

    public Minvo(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        moving();
        dieAnimation();
    }

    @Override
    public void updateTile(int tx, int ty) {
        if (BombermanGame.mapMatrix[ConvertCordinate.getTileY(ty)][ConvertCordinate.getTileX(tx)] != 2 && BombermanGame.mapMatrix[ConvertCordinate.getTileY(ty)][ConvertCordinate.getTileX(tx)] != 1) {
            BombermanGame.mapMatrix[ConvertCordinate.getTileY(ty)][ConvertCordinate.getTileX(tx)] = ' ';
            BombermanGame.mapMatrix[ConvertCordinate.getTileY(y)][ConvertCordinate.getTileX(x)] = '3';
        }
    }

    public String getNextDirection() {
        if (canMove(x, y, currentMove, this.speed)) {
            return currentMove;
        }
        i++;
        int j = i % 8;
        currentMove = directions[j];
        return currentMove;
    }

    public void moving() {
        if (isAlive == true) {
            String nextMove = getNextDirection();
            int tempX = x;
            int tempY = y;
            switch (nextMove) {
                case "LEFT":
                    this.x -= speed;
                    setImg(Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, x, 15).getImage());
                    break;
                case "RIGHT":
                    this.x += speed;
                    setImg(Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, x, 15).getImage());
                    break;
                case "UP":
                    this.y-=speed;
                    setImg(Sprite.movingSprite(Sprite.minvo_left1,Sprite.minvo_right2,Sprite.minvo_left3,y,15).getImage());
                    break;
                case "DOWN":
                    this.y+=speed;
                    setImg(Sprite.movingSprite(Sprite.minvo_right1,Sprite.minvo_left2,Sprite.minvo_right3,y,15).getImage());
            }
            updateTile(tempX, tempY);
        }
    }

    @Override
    public void setImg(Image img) {
        super.setImg(img);
    }

    @Override
    public void die() {
        isAlive = false;
        speed = 0;
        BombermanGame.mapMatrix[ConvertCordinate.getTileY(y)][ConvertCordinate.getTileX(x)] = ' ';
    }

    public void remove() {
        BombermanGame.removeEntities(this);
        BombermanGame.enemyCount -= 1;
        BombermanGame.score += 30;

    }

    void dieAnimation() {
        if (isAlive == false) {
            dieTime--;
            if(dieTime==180){
                setImg(Sprite.mob_dead1.getImage());
            }
            if(dieTime==120){
                setImg(Sprite.mob_dead2.getImage());
            }
            if(dieTime==60){
                setImg(Sprite.mob_dead3.getImage());
            }
        }
        if (dieTime == 0) {
            remove();
        }

    }
}
