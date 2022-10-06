package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Entity {
    boolean isRevealed;
    boolean isOpened;
    public Portal(int x, int y, Image img) {
        super(x, y, img);
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    @Override
    public void setImg(Image img) {
        super.setImg(img);
    }

    @Override
    public void update(){
        if(isRevealed){
            this.setImg(Sprite.portal.getFxImage());
        }
        //TODO
        /*
        Neu open thi qua lv
         */
        if(isOpened){

        }
    }
}
