package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.map.ConvertCordinate;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    private char[][] mapMatrix;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    // Lấy object trong list;
    public static Entity getEntity(int x,int y){
        x= ConvertCordinate.getTileX(x);
        y=ConvertCordinate.getTileY(y);
        for(int i=0;i<BombermanGame.getEntities().size();i++){
            if(BombermanGame.getEntities().get(i).getX()/Sprite.SCALED_SIZE==x&&BombermanGame.getEntities().get(i).getY()/Sprite.SCALED_SIZE==y){
                return BombermanGame.getEntities().get(i);
            }
        }
        return null;
    }
    public static Entity getEntityFromTile(int tx, int ty){
        for(int i=0;i<BombermanGame.getEntities().size();i++){
            if(ConvertCordinate.getTileX(BombermanGame.getEntities().get(i).getX())==tx&&ConvertCordinate.getTileY(BombermanGame.getEntities().get(i).getY())==ty){
                return BombermanGame.getEntities().get(i);
            }
        }
        return null;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();
}
