package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.MapEntities.BombermanGame;
import uet.oop.bomberman.map.ConvertCordinate;
import uet.oop.bomberman.entities.MovingEntities.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class itemBomb extends Item {
    Bomber bomber;
    private boolean isTaken = false;
    private boolean isRevealed = false;
    private int CountDownTime = 2000;

    public itemBomb(int x, int y, Image img) {
        super(x, y, img);
        isTaken = false;
        isRevealed = false;
    }

    @Override
    public void setImg(Image img) {
        super.setImg(img);
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
        BombermanGame.MovableMap[ConvertCordinate.getTileY(y)][ConvertCordinate.getTileX(x)] = 1;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    @Override
    public void addToBomber(Bomber bomber) {
        isTaken = true;
        //them 1 bomb
        this.bomber = bomber;
        BombermanGame.itemMap[ConvertCordinate.getTileY(y)][ConvertCordinate.getTileX(x)] = ' ';
    }

    public void countdown() {
        if (isTaken && CountDownTime > 0) {
            CountDownTime--;
        }
        if (CountDownTime == 0) {
            isTaken = false;
            bomber.setBombMax(1);
            remove();
        }
    }

    public void remove() {
        BombermanGame.removeEntities(this);
    }

    @Override
    public void update() {
        if (isRevealed) {
            setImg(Sprite.powerup_bombs.getFxImage());
        }
        if (isTaken) {
            if (bomber.isAlive) {
                setImg(Sprite.grass.getFxImage());
                bomber.setBombMax(2);
            }
        }
        countdown();
    }
}
