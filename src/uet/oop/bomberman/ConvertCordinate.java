package uet.oop.bomberman;

import uet.oop.bomberman.graphics.Sprite;

public class ConvertCordinate{
    public static int PixelToTile(double i){
        /*if((i+16)%32>=10){
            return (int) (i/Sprite.SCALED_SIZE)+1;
        }

         */
        return (int) i/Sprite.SCALED_SIZE;
    }
    public static int TileToPixel(int i){
        return i*Sprite.SCALED_SIZE;
    }
    public static double TileToPixel(double i){
        return (int)(i*Sprite.SCALED_SIZE);
    }
}
