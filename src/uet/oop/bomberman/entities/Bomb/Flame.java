package uet.oop.bomberman.entities.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.MapEntities.BombermanGame;
import uet.oop.bomberman.entities.Entity;

public class Flame extends Entity {
    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void setImg(Image img) {
        super.setImg(img);
    }

    public void remove() {
        BombermanGame.removeEntities(this);
    }

    @Override
    public void update() {

    }
}
