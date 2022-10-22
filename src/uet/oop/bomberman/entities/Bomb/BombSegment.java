package uet.oop.bomberman.entities.Bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Item.itemBomb;
import uet.oop.bomberman.entities.Item.itemFlame;
import uet.oop.bomberman.entities.Item.itemSpeed;
import uet.oop.bomberman.entities.MapEntities.Brick;
import uet.oop.bomberman.entities.MapEntities.Portal;
import uet.oop.bomberman.entities.MovingEntities.AnimatedObject;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public class BombSegment extends Entity {
    ArrayList<Flame> flamesLEFT = new ArrayList<>();
    ArrayList<Flame> flamesRIGHT = new ArrayList<>();
    ArrayList<Flame> flamesUP = new ArrayList<>();
    ArrayList<Flame> flamesDOWN = new ArrayList<>();
    private int centerX;
    private int centerY;
    private int LengthLeft;
    private int LengthRight;
    private int LengthUp;
    private int LengthDown;
    private int LengthDefault = 1;
    private int FlameTime = 30;

    public BombSegment(int x, int y, Image image, int LengthDefault) {
        super(x, y, image);
        this.centerX = x;
        this.centerY = y;
        this.LengthDefault=LengthDefault;
        SetFlame();

    }

    public void setLength() {
        LengthUp = LengthDown = LengthLeft = LengthRight = LengthDefault;
    }

    public void calculate() {
        for (int i = 1; i <= LengthDefault; i++) {
            if (BombermanGame.mapMatrix[centerY][centerX - i] == '#' ) {
                LengthLeft = i - 1;
                break;
            }
        }
        for (int i = 1; i <= LengthDefault; i++) {
            if (BombermanGame.mapMatrix[centerY][centerX + i] == '#') {
                LengthRight = i - 1;
                break;
            }
        }
        for (int i = 1; i <= LengthDefault; i++) {
            if (BombermanGame.mapMatrix[centerY - i][centerX] == '#') {
                LengthUp = i - 1;
                break;
            }
        }
        for (int i = 1; i <= LengthDefault; i++) {
            if (BombermanGame.mapMatrix[centerY + i][centerX] == '#') {
                LengthDown = i - 1;
                break;
            }
        }
    }

    public void calculateforDisplay() {
        for (int i = 1; i <= LengthDefault; i++) {
            if (BombermanGame.mapMatrix[centerY][centerX - i] == '*' || BombermanGame.mapMatrix[centerY][centerX - i] == '#' || BombermanGame.itemMap[centerY][centerX - i]!=' ') {
                LengthLeft = i - 1;
                break;
            }
        }
        for (int i = 1; i <= LengthDefault; i++) {
            if (BombermanGame.mapMatrix[centerY][centerX + i] == '*' || BombermanGame.mapMatrix[centerY][centerX + i] == '#'||BombermanGame.itemMap[centerY][centerX+i]!=' ') {
                LengthRight = i - 1;
                break;
            }
        }
        for (int i = 1; i <= LengthDefault; i++) {
            if (BombermanGame.mapMatrix[centerY - i][centerX] == '*' || BombermanGame.mapMatrix[centerY - i][centerX] == '#'||BombermanGame.itemMap[centerY-i][centerX]!=' ') {
                LengthUp = i - 1;
                break;
            }
        }
        for (int i = 1; i <= LengthDefault; i++) {
            if (BombermanGame.mapMatrix[centerY + i][centerX] == '*' || BombermanGame.mapMatrix[centerY + i][centerX] == '#'||BombermanGame.itemMap[centerY+i][centerX]!=' ') {
                LengthDown = i - 1;
                break;
            }
        }
    }

    public void checkCollide() {
        //LEFT
        for (int i = 0; i <= LengthLeft; i++) {
            if (getEntityFromTile(centerX - i, centerY) instanceof Brick) {
                ((Brick) (getEntityFromTile(centerX - i, centerY))).Break();
            }
            if (getEntityFromTile(centerX - i, centerY) instanceof AnimatedObject) {
                ((AnimatedObject) getEntityFromTile(centerX - i, centerY)).die();
            }
            if (getEntityFromTile(centerX - i, centerY) instanceof itemBomb) {
                ((itemBomb) getEntityFromTile(centerX - i, centerY)).setRevealed(true);
            }
            if (getEntityFromTile(centerX - i, centerY) instanceof itemSpeed) {
                ((itemSpeed) getEntityFromTile(centerX - i, centerY)).setRevealed(true);
            }
            if (getEntityFromTile(centerX - i, centerY) instanceof itemFlame) {
                ((itemFlame) getEntityFromTile(centerX - i, centerY)).setRevealed(true);
            }
            if(getEntityFromTile(centerX-i,centerY) instanceof Portal){
                ((Portal) getEntityFromTile(centerX-i,centerY)).setRevealed(true);
            }
            if(getEntityFromTile(centerX-i,centerY) instanceof Bomb){
                ((Bomb) getEntityFromTile(centerX - i, centerY)).setCountDownTime(0);
            }
        }
        for (int i = 0; i <= LengthRight; i++) {
            if (getEntityFromTile(centerX + i, centerY) instanceof Brick) {
                ((Brick) (getEntityFromTile(centerX + i, centerY))).Break();
            }
            if (getEntityFromTile(centerX + i, centerY) instanceof AnimatedObject) {
                ((AnimatedObject) getEntityFromTile(centerX + i, centerY)).die();
            }
            if (getEntityFromTile(centerX + i, centerY) instanceof itemBomb) {
                ((itemBomb) getEntityFromTile(centerX + i, centerY)).setRevealed(true);
            }
            if (getEntityFromTile(centerX + i, centerY) instanceof itemSpeed) {
                ((itemSpeed) getEntityFromTile(centerX + i, centerY)).setRevealed(true);
            }
            if (getEntityFromTile(centerX + i, centerY) instanceof itemFlame) {
                ((itemFlame) getEntityFromTile(centerX + i, centerY)).setRevealed(true);
            }
            if(getEntityFromTile(centerX+i,centerY) instanceof Portal){
                ((Portal) getEntityFromTile(centerX+i,centerY)).setRevealed(true);
            }
            if(getEntityFromTile(centerX+i,centerY) instanceof Bomb){
                ((Bomb) getEntityFromTile(centerX + i, centerY)).setCountDownTime(0);
            }
        }
        for (int i = 0; i <= LengthUp; i++) {
            if (getEntityFromTile(centerX, centerY - i) instanceof Brick) {
                ((Brick) (getEntityFromTile(centerX, centerY - i))).Break();
            }
            if (getEntityFromTile(centerX, centerY - i) instanceof AnimatedObject) {
                ((AnimatedObject) getEntityFromTile(centerX, centerY - i)).die();
            }
            if (getEntityFromTile(centerX, centerY - i) instanceof itemBomb) {
                ((itemBomb) getEntityFromTile(centerX, centerY - i)).setRevealed(true);
            }
            if (getEntityFromTile(centerX, centerY - i) instanceof itemSpeed) {
                ((itemSpeed) getEntityFromTile(centerX, centerY - i)).setRevealed(true);
            }
            if(getEntityFromTile(centerX,centerY-i) instanceof Portal){
                ((Portal) getEntityFromTile(centerX,centerY-i)).setRevealed(true);
            }
            if(getEntityFromTile(centerX,centerY-i) instanceof Bomb){
                ((Bomb) getEntityFromTile(centerX, centerY-i)).setCountDownTime(0);
            }
            if (getEntityFromTile(centerX, centerY-i) instanceof itemFlame) {
                ((itemFlame) getEntityFromTile(centerX , centerY-i)).setRevealed(true);
            }
        }
        for (int i = 0; i <= LengthDown; i++) {
            if (getEntityFromTile(centerX, centerY + i) instanceof Brick) {
                ((Brick) (getEntityFromTile(centerX, centerY + i))).Break();
            }
            if (getEntityFromTile(centerX, centerY + i) instanceof AnimatedObject) {
                ((AnimatedObject) getEntityFromTile(centerX, centerY + i)).die();
            }
            if (getEntityFromTile(centerX, centerY + i) instanceof itemBomb) {
                ((itemBomb) getEntityFromTile(centerX, centerY + i)).setRevealed(true);
            }
            if (getEntityFromTile(centerX, centerY + i) instanceof itemSpeed) {
                ((itemSpeed) getEntityFromTile(centerX, centerY + i)).setRevealed(true);
            }
            if(getEntityFromTile(centerX,centerY+i) instanceof Portal){
                ((Portal) getEntityFromTile(centerX,centerY+i)).setRevealed(true);
            }
            if (getEntityFromTile(centerX , centerY+i) instanceof itemFlame) {
                ((itemFlame) getEntityFromTile(centerX, centerY+i)).setRevealed(true);
            }
            if(getEntityFromTile(centerX,centerY+i) instanceof Bomb){
                ((Bomb) getEntityFromTile(centerX, centerY+i)).setCountDownTime(0);
            }
        }
    }

    public void remove() {
        BombermanGame.removeEntities(this);
    }

    public void SetFlame() {
        setLength();
        calculate();
        checkCollide();
        calculateforDisplay();
        inital();
    }

    public void inital() {

        if (LengthLeft > 0) {
            for (int i = 1; i < LengthLeft; i++) {
                Flame flameLeft = new Flame(centerX - i, centerY, Sprite.explosion_horizontal.getImage());
                flamesLEFT.add(flameLeft);
                BombermanGame.addEntities(flameLeft);
            }
            Flame flameLeftLast = new Flame(centerX - LengthLeft, centerY, Sprite.explosion_horizontal_left_last.getImage());
            flamesLEFT.add(flameLeftLast);
            BombermanGame.addEntities(flameLeftLast);
        }
        if (LengthRight > 0) {
            for (int i = 1; i < LengthRight; i++) {
                Flame flameRight = new Flame(centerX + i, centerY, Sprite.explosion_horizontal.getImage());
                flamesRIGHT.add(flameRight);
                BombermanGame.addEntities(flameRight);
            }
            Flame flameRightLast = new Flame(centerX + LengthRight, centerY, Sprite.explosion_horizontal_right_last.getImage());
            flamesRIGHT.add(flameRightLast);
            BombermanGame.addEntities(flameRightLast);
        }
        if (LengthDown > 0) {
            for (int i = 1; i < LengthDown; i++) {
                Flame flameDown = new Flame(centerX, centerY + i, Sprite.explosion_vertical.getImage());
                flamesDOWN.add(flameDown);
                BombermanGame.addEntities(flameDown);
            }
            Flame flameDownLast = new Flame(centerX, centerY + LengthDown, Sprite.explosion_vertical_down_last.getImage());
            flamesDOWN.add(flameDownLast);
            BombermanGame.addEntities(flameDownLast);
        }
        if (LengthUp > 0) {
            for (int i = 1; i < LengthUp; i++) {
                Flame flameUp = new Flame(centerX, centerY - i, Sprite.explosion_vertical.getImage());
                flamesUP.add(flameUp);
                BombermanGame.addEntities(flameUp);
            }
            Flame flameUpLast = new Flame(centerX, centerY - LengthUp, Sprite.explosion_vertical_top_last.getImage());
            flamesUP.add(flameUpLast);
            BombermanGame.addEntities(flameUpLast);
        }
    }

    public void display() {
        if (FlameTime == 10) {
            setImg(Sprite.bomb_exploded2.getImage());
            if (flamesLEFT.size() > 0) {
                for (int i = 0; i < flamesLEFT.size(); i++) {
                    flamesLEFT.get(i).setImg(Sprite.explosion_horizontal2.getImage());
                }
                flamesLEFT.get(flamesLEFT.size() - 1).setImg(Sprite.explosion_horizontal_left_last2.getImage());

            }
            if (flamesRIGHT.size() > 0) {
                for (int i = 0; i < flamesRIGHT.size(); i++) {
                    flamesRIGHT.get(i).setImg(Sprite.explosion_horizontal2.getImage());
                }
                flamesRIGHT.get(flamesRIGHT.size() - 1).setImg(Sprite.explosion_horizontal_right_last2.getImage());
            }
            if (flamesUP.size() > 0) {
                for (int i = 0; i < flamesUP.size(); i++) {
                    flamesUP.get(i).setImg(Sprite.explosion_vertical2.getImage());
                }
                flamesUP.get(flamesUP.size() - 1).setImg(Sprite.explosion_vertical_top_last2.getImage());
            }
            if (flamesDOWN.size() > 0) {
                for (int i = 0; i < flamesDOWN.size(); i++) {
                    flamesDOWN.get(i).setImg(Sprite.explosion_vertical2.getImage());
                }
                flamesDOWN.get(flamesDOWN.size() - 1).setImg(Sprite.explosion_vertical_down_last2.getImage());
            }
        }
        if (FlameTime == 20) {
            setImg(Sprite.bomb_exploded1.getImage());
            if (flamesLEFT.size() > 0) {
                for (int i = 0; i < flamesLEFT.size(); i++) {
                    flamesLEFT.get(i).setImg(Sprite.explosion_horizontal1.getImage());
                }
                flamesLEFT.get(flamesLEFT.size() - 1).setImg(Sprite.explosion_horizontal_left_last1.getImage());

            }
            if (flamesRIGHT.size() > 0) {
                for (int i = 0; i < flamesRIGHT.size(); i++) {
                    flamesRIGHT.get(i).setImg(Sprite.explosion_horizontal1.getImage());
                }
                flamesRIGHT.get(flamesRIGHT.size() - 1).setImg(Sprite.explosion_horizontal_right_last1.getImage());
            }
            if (flamesUP.size() > 0) {
                for (int i = 0; i < flamesUP.size(); i++) {
                    flamesUP.get(i).setImg(Sprite.explosion_vertical1.getImage());
                }
                flamesUP.get(flamesUP.size() - 1).setImg(Sprite.explosion_vertical_top_last1.getImage());

            }
            if (flamesDOWN.size() > 0) {
                for (int i = 0; i < flamesDOWN.size(); i++) {
                    flamesDOWN.get(i).setImg(Sprite.explosion_vertical1.getImage());
                }
                flamesDOWN.get(flamesDOWN.size() - 1).setImg(Sprite.explosion_vertical_down_last1.getImage());


            }
        }
        FlameTime--;
      if(FlameTime==0){
            for(int i=0;i<flamesRIGHT.size();i++){
                flamesRIGHT.get(i).remove();
            }
            for(int i=0;i<flamesLEFT.size();i++){
                flamesLEFT.get(i).remove();
            }
            for(int i=0;i<flamesUP.size();i++){
                flamesUP.get(i).remove();
            }
            for(int i=0;i<flamesDOWN.size();i++){
                flamesDOWN.get(i).remove();
            }
            remove();
        }
    }

    @Override
    public void update() {
        checkCollide();
        display();
    }

}
