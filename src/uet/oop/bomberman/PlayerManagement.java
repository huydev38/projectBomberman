package uet.oop.bomberman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayerManagement {
    public static   ArrayList<Player> playerList = new ArrayList<>();
    public static   ArrayList<Player> topPlayer = new ArrayList<>();
    public static void getList() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("res/ranking.txt"));
        while (sc.hasNext()){
            Player player = new Player();
            player.setName(sc.next());
            player.setScore(sc.nextInt());
            player.setTime(sc.nextInt());
            playerList.add(player);
        }
        sc.close();
    }
    public static void printPlayerList() throws FileNotFoundException {
        getTop();
        for(int i=0;i<topPlayer.size();i++){
           System.out.print(topPlayer.get(i).toString());
        }
    }
    public static void getTop(){
        try {
            getList();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        int top =0;

        while(top!=5) {
            Player tmp = playerList.get(0);
            for (int i = 0; i < playerList.size(); i++) {
                if(tmp.isBetter(playerList.get(i))==0){
                    tmp=playerList.get(i);
                }
            }
            topPlayer.add(tmp);
            playerList.remove(tmp);
            top++;
        }
    }
}
