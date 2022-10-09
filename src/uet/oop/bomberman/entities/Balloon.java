package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.ConvertCordinate;
import uet.oop.bomberman.graphics.Sprite;


public class Balloon extends AnimatedObject{
    private final int speed=1;
    String[] directions = {"LEFT","RIGHT"};
    String currentMove="LEFT";
    int i=0;
    public Balloon(int x, int y, Image img){
        super(x,y,img);
    }

    @Override
    public void update() {
        moving();
    }

    @Override
    public void updateTile(int tx, int ty) {
        BombermanGame.mapMatrix[ConvertCordinate.getTileY(ty)][ConvertCordinate.getTileX(tx)]=' ';
        BombermanGame.mapMatrix[ConvertCordinate.getTileY(y)][ConvertCordinate.getTileX(x)]='1';
    }

    public String getNextDirection(){
        if(canMove(x,y,currentMove,this.speed)){
            return currentMove;
        }
        i++;
        int j = i%2;
        currentMove = directions[j];
        return currentMove;
    }
    public void moving(){
        String nextMove=getNextDirection();
        int tempX=x;
        int tempY=y;
        switch (nextMove){
            case "LEFT":
                this.x-=speed;
                setImg(Sprite.movingSprite(Sprite.balloom_left1,Sprite.balloom_left2,Sprite.balloom_left3,x,15).getImage());
                break;
            case "RIGHT":
                this.x+=speed;
                setImg(Sprite.movingSprite(Sprite.balloom_right1,Sprite.balloom_right2,Sprite.balloom_right3,x,15).getImage());
                break;
        }
        updateTile(tempX,tempY);
    }

    @Override
    public void setImg(Image img) {
        super.setImg(img);
    }

}
