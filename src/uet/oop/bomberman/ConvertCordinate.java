package uet.oop.bomberman;

import uet.oop.bomberman.graphics.Sprite;

public class ConvertCordinate{
    public static int PixelToTile(double i){
        return (int)(i/ Sprite.DEFAULT_SIZE);
    }
    public static int TileToPixel(int i){
        return i*Sprite.DEFAULT_SIZE;
    }
    public static double TileToPixel(double i){
        return (int)(i*Sprite.DEFAULT_SIZE);
    }
}
