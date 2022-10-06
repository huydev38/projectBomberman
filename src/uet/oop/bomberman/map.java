package uet.oop.bomberman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class map {
    private int h=100, w=100;
    private char[][] mapMatrix = new char[h][w];

    public map() {

    }

    public char[][] getMapMatrix() {
        return mapMatrix;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    public char getCharacter(int x, int y) {
        return mapMatrix[x][y];
    }

    public void loadMap(String src){
        Scanner sc = null;
        try {
            sc = new Scanner(new File(src));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int lv = sc.nextInt();
        h=sc.nextInt();
        w=sc.nextInt();
        String temp=sc.nextLine();
        for(int i=0;i<h;i++){
            String line=sc.nextLine();
            for(int j=0;j<w;j++){
                mapMatrix[i][j]=line.charAt(j);
            }
        }
    }
}