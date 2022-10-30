package uet.oop.bomberman.entities.MovingEntities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.map.ConvertCordinate;
import uet.oop.bomberman.entities.Entity;

public abstract class AnimatedObject extends Entity {

    public AnimatedObject(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {

    }
    public boolean canMove(int x, int y, String direction, int speed) {
        int xm = x;
        int ym = y;
        int xn = x;
        int yn = y;
        switch (direction) {
            case "LEFT":
                xm = ConvertCordinate.PixelToTile(x - speed);
                ym = ConvertCordinate.PixelToTile(y + 3 );
                xn = ConvertCordinate.PixelToTile(x - speed);
                yn = ConvertCordinate.PixelToTile(y + 26);
                break;
            case "RIGHT":
                xm = ConvertCordinate.PixelToTile(x + speed + 26);
                ym = ConvertCordinate.PixelToTile(y + 3);
                xn = ConvertCordinate.PixelToTile(x + speed + 26);
                yn = ConvertCordinate.PixelToTile(y + 26);
                break;
            case "UP":
                xm = ConvertCordinate.PixelToTile(x + 3);
                ym = ConvertCordinate.PixelToTile(y - speed);
                xn = ConvertCordinate.PixelToTile(x + 26);
                yn = ConvertCordinate.PixelToTile(y -speed);
                break;
            case "DOWN":
                xm = ConvertCordinate.PixelToTile(x + 3);
                ym = ConvertCordinate.PixelToTile(y + speed+26);
                xn = ConvertCordinate.PixelToTile(x + 26);
                yn = ConvertCordinate.PixelToTile(y + speed+26);
        }

        return BombermanGame.mapMatrix[ym][xm] != '*' && BombermanGame.mapMatrix[ym][xm] != '#' && BombermanGame.mapMatrix[yn][xn] != '*' && BombermanGame.mapMatrix[yn][xn] != '#' && BombermanGame.MovableMap[yn][xn] != 0 && BombermanGame.MovableMap[ym][xm] != 0;
    }
    public abstract void updateTile(int tx, int ty);
    public abstract void die();

}
