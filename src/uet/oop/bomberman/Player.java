package uet.oop.bomberman;

import java.io.FileWriter;
import java.io.IOException;

public class Player {
    private String name;
    private int score;
    private int time;
    public Player(){}
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

    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    public int getTime() {
        return time;
    }

    public void WriteToFile(){
        try{
            FileWriter fw = new FileWriter("res/ranking.txt",true);
            fw.write(name + " " + score + " " + time+"\n");
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("%10s",name));
        res.append(String.format("%10d",score));
        res.append(String.format("%10d",time));
        return res.toString()+"\n";
    }

    public int isBetter(Player another){
        if(this.score>another.getScore()){
            return 1;
        }else if(this.score<another.getScore()){
            return 0;
        }else if(this.score==another.getScore()){
            if(this.time>another.getTime()){
                return 0;
            }else if(this.time<another.getTime()){
                return 1;
            }
        }
        return 0;
    }
}
