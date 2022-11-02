package uet.oop.bomberman.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
    private int h = 13, w = 31;
    private final char[][] mapMatrix = new char[h][w];
    private final int[][] MovableMap = new int[h][w];
    private final char[][] itemMap = new char[h][w];

    public Map() {

    }

    public char[][] getMapMatrix() {
        return mapMatrix;
    }

    public int[][] getMovableMap() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (mapMatrix[i][j] == 'f' || mapMatrix[i][j] == 'b' || mapMatrix[i][j] == 's' || mapMatrix[i][j] == 'x' || mapMatrix[i][j] == '*' || mapMatrix[i][j] == '#') {
                    MovableMap[i][j] = 0;
                } else {
                    MovableMap[i][j] = 1;
                }
            }
        }
        return MovableMap;
    }

    public void setMapMatrix(int x, int y, char c) {
        mapMatrix[x][y] = c;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    public char[][] getItemMap() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (mapMatrix[i][j] == 'f' || mapMatrix[i][j] == 'b' || mapMatrix[i][j] == 's' || mapMatrix[i][j] == 'x') {
                    itemMap[i][j] = mapMatrix[i][j];
                } else {
                    itemMap[i][j] = ' ';
                }
            }
        }
        return itemMap;
    }

    public char getCharacter(int x, int y) {
        return mapMatrix[x][y];
    }

    public void loadMap(String src) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(src));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int lv = sc.nextInt();
        h = sc.nextInt();
        w = sc.nextInt();
        String temp = sc.nextLine();
        for (int i = 0; i < h; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < w; j++) {
                mapMatrix[i][j] = line.charAt(j);
            }
        }
    }
}