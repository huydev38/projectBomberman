package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.MapEntities.BombermanGame;
import uet.oop.bomberman.map.ConvertCordinate;
import uet.oop.bomberman.entities.MovingEntities.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Item {
    boolean isRevealed = false;
    boolean isOpened = false;

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
    public void addToBomber(Bomber bomber) {
        if (isOpened) {
            BombermanGame.endGame = true;
            BombermanGame.playSE(6);
        }
    }

    @Override
    public void update() {
        if (isRevealed) {
            this.setImg(Sprite.portal.getFxImage());
            BombermanGame.MovableMap[ConvertCordinate.getTileY(y)][ConvertCordinate.getTileX(x)] = 1;
        }
        /*
        Neu open thi qua lv
         */
        if (BombermanGame.enemyCount == 0) {
            isOpened = true;
        }
    }
}
