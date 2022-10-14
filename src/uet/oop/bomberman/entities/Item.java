package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class Item extends Entity{
    public Item(int x, int y, Image img) {
        super(x, y, img);

    }
    public static Item getItem(int x, int y) {
        if (getEntity(x, y) instanceof itemBomb) {
            return (itemBomb) getEntity(x, y);
        }
        if (getEntity(x, y) instanceof itemSpeed) {
            return (itemSpeed) getEntity(x, y);
        }
        if (getEntity(x, y) instanceof itemFlame) {
            return (itemFlame) getEntity(x, y);
            //if(getEntity(x,y) instanceof itemFlame){
            //    return (itemFlame)getEntity(x,y);
            //
        }
        return null;
    }
    public abstract void addToBomber(Bomber bomber);
    @Override
    public void update() {
    }
}
