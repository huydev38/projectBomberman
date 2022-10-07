package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class AnimatedObject extends Entity{
    private char[][] mapMatrix;
    public AnimatedObject(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {

    }
    public abstract boolean canMove(char [][]mapMatrix, int xm, int ym, int xn, int yn);


}
