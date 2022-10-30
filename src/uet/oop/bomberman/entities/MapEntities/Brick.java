package uet.oop.bomberman.entities.MapEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.map.ConvertCordinate;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
    private boolean isBroken=false;
    private int breakTime = 20*3;
    public Brick(int x, int y, Image img) {
        super( x, y, img);
    }

    public void displayBreak(){
        if(isBroken==true){
            if(breakTime==20*3){
                setImg(Sprite.brick_exploded.getImage());
            }
            if(breakTime==15*3){
                setImg(Sprite.brick_exploded1.getImage());
            }
            if(breakTime==10*3){
                setImg(Sprite.brick_exploded2.getImage());
            }
            breakTime--;
        }
        if(breakTime==0){
            remove();
        }
    }
    public void remove(){
        BombermanGame.mapMatrix[ConvertCordinate.getTileY(y)][ConvertCordinate.getTileX(x)]=' ';
        BombermanGame.MovableMap[ConvertCordinate.getTileY(y)][ConvertCordinate.getTileX(x)]=1;
        BombermanGame.removeEntities(this);
    }
    public void Break(){
        isBroken=true;
    }
    @Override
    public void update() {
        displayBreak();
    }
}