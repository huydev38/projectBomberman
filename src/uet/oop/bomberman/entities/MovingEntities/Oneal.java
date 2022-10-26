package uet.oop.bomberman.entities.MovingEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.ConvertCordinate;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends AnimatedObject {
    private int speed = 1;
    private boolean isAlive = true;
    private int dieTime = 180;
    String[] directions = {"RIGHT", "LEFT"};
    String currentMove = "LEFT";
    int i = 0;

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        speed = (i + 2) % 3 + 1;
        moving();
        dieAnimation();
    }

    @Override
    public void updateTile(int tx, int ty) {
        if (BombermanGame.mapMatrix[ConvertCordinate.getTileY(ty)][ConvertCordinate.getTileX(tx)] != 1 && BombermanGame.mapMatrix[ConvertCordinate.getTileY(ty)][ConvertCordinate.getTileX(tx)] != 3){

            BombermanGame.mapMatrix[ConvertCordinate.getTileY(ty)][ConvertCordinate.getTileX(tx)] = ' ';
        BombermanGame.mapMatrix[ConvertCordinate.getTileY(y)][ConvertCordinate.getTileX(x)] = '2';
    }

}

    public String getNextDirection() {
        if (canMove(x, y, currentMove, this.speed)) {
            return currentMove;
        }
        i++;
        int j = i % 2;
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
                    setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, x, 15).getImage());
                    break;
                case "RIGHT":
                    this.x += speed;
                    setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, x, 15).getImage());
                    break;
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
        speed=0;

        BombermanGame.mapMatrix[ConvertCordinate.getTileY(y)][ConvertCordinate.getTileX(x)]=' ';
    }
    public void remove(){
        BombermanGame.removeEntities(this);
        BombermanGame.score+=20;
        BombermanGame.enemyCount-=1;
    }
    void dieAnimation() {
        if (isAlive == false) {
            dieTime--;
            setImg(Sprite.oneal_dead.getImage());
        }
        if(dieTime==0){
            remove();
        }

    }
}
