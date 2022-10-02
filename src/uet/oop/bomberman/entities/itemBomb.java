package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class itemBomb extends Item{
    private boolean isTaken;
    private boolean isRevealed;
    public itemBomb(int x, int y, Image img) {
        super(x, y, img);
        isTaken=false;
        isRevealed=false;
    }

    @Override
    public void setImg(Image img) {
        super.setImg(img);
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    @Override
    public void addToBomber(Bomber bomber) {
        isTaken=true;
        //them 1 bomb
        bomber.setBombCount(bomber.getBombCount()+1);
    }

    @Override
    public void update() {
        if(isRevealed){
            setImg(Sprite.powerup_bombs.getFxImage());
        }
        if(isTaken){
            setImg(Sprite.grass.getFxImage());
        }
    }
}
