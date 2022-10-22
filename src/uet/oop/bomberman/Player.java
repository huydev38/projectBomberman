package uet.oop.bomberman;

import java.io.FileWriter;
import java.io.IOException;

public class Player {
    private String name;
    private int score;
    private int time;
    public Player(String name, int score, int time){
        this.name=name;
        this.score=score;
        this.time=time;
    }
    public Player(String name){
        this.name=name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTime(int time) {
        this.time = time;
    }
    public void WriteToFile(){
        try{
            FileWriter fw = new FileWriter("/res/ranking.txt");
            fw.write(name + " " + score + " " + time);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
