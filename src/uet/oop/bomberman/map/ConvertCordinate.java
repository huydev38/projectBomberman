package uet.oop.bomberman.map;

import uet.oop.bomberman.graphics.Sprite;

public class ConvertCordinate{
    public static int PixelToTile(double i){
        /*if((i+16)%32>=10){
            return (int) (i/Sprite.SCALED_SIZE)+1;
        }

         */
        return (int) i/Sprite.SCALED_SIZE;
    }
    public static int getTileX(int x){
        if((x/Sprite.SCALED_SIZE)+1==(x+5)/Sprite.SCALED_SIZE){
            return (x/Sprite.SCALED_SIZE) +1;
        }else{
            return x/Sprite.SCALED_SIZE;
        }
    }
    public static int getTileY(int y){
        if((y/Sprite.SCALED_SIZE)+1==(y+5)/Sprite.SCALED_SIZE){
            return (y/Sprite.SCALED_SIZE) +1;
        }else{
            return y/Sprite.SCALED_SIZE;
        }
    }
    public static int TileToPixel(int i){
        return i*Sprite.SCALED_SIZE;
    }
    public static double TileToPixel(double i){
        return (int)(i*Sprite.SCALED_SIZE);
    }

}
