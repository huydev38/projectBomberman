package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Item extends Entity{
    public Item(int x, int y, Image img) {
        super(x, y, img);

    }
    public void addToBomber(Bomber bomber){}
    @Override
    public void update() {
    }
}
