package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Item.itemBomb;
import uet.oop.bomberman.entities.Item.itemFlame;
import uet.oop.bomberman.entities.Item.itemSpeed;
import uet.oop.bomberman.entities.MapEntities.Brick;
import uet.oop.bomberman.entities.MapEntities.Grass;
import uet.oop.bomberman.entities.MapEntities.Portal;
import uet.oop.bomberman.entities.MapEntities.Wall;
import uet.oop.bomberman.entities.MovingEntities.Balloon;
import uet.oop.bomberman.entities.MovingEntities.Bomber;
import uet.oop.bomberman.entities.MovingEntities.Oneal;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    Stage window;
    Scene sceneMenu;
    private final int startTime = 300;
    private Integer seconds=startTime;


    public int WIDTH=50;
    public int HEIGHT=50;

    public  int level;
    public static int score = 0;
    public static int bombCount =1;
    Map levelMap = new Map();
    
    private GraphicsContext gc;
    private Canvas canvas;
    private static  List<Entity> entities = new ArrayList<>();
    private static  List<Entity> stillObjects = new ArrayList<>();

    public static void removeEntities(Entity e){
        entities.remove(e);
    }

    public static void addEntities(Entity e){
        entities.add(e);
    }
    public static List<Entity> getEntities(){
        return entities;
    }

    public static char[][]mapMatrix;
    public static int[][] MovableMap;
    public static char[][]itemMap;
    Label Score = new Label();
    Label BombCount = new Label();

    Group root = new Group();
    public static void main(String[] args) {
        Application.launch(args);
    }
    //TODO
    /*
    Can them background, can giua cho nut, them scene chon level sau sceneMenu
     */
    @Override
    public void start(Stage stage) {
        window=stage;
        window.setTitle("BomberMan");
        Button buttonPlay = new Button("Play");
        Button buttonRanking = new Button("Ranking Board");
        VBox layoutMenu = new VBox();
        sceneMenu = new Scene(layoutMenu, 1080,400);
        window.setScene(sceneMenu);
        layoutMenu.getChildren().addAll(buttonPlay, buttonRanking);
        buttonPlay.setOnAction(event ->{
            startGame(window);
        });
        buttonRanking.setOnAction(event ->{
            displayRanking(window);
        });
        window.show();
}
    //TODO
    /*
    Them nut quay lai scene menu
     */
    public void displayRanking(Stage window){

    }
    public void startGame(Stage stage){
        createMap();
        mapMatrix= levelMap.getMapMatrix();
        MovableMap=levelMap.getMovableMap();
        itemMap=levelMap.getItemMap();
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * (HEIGHT));
        gc = canvas.getGraphicsContext2D();
        // Tao root container

        root.getChildren().add(canvas);
        HBox layout = new HBox();

        root.getChildren().add(layout);
        layout.getChildren().add(BombCount);
        layout.getChildren().add(Score);

        layout.setAlignment(Pos.TOP_CENTER);
        layout.setAlignment(Pos.BASELINE_CENTER);
        layout.setSpacing(200);
        displayScore();
        doTime(layout);
        displayBombCount();
        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
              //  System.out.println(entities.size());
//         for(int i=0;i< levelMap.getH();i++){
//                    for(int j=0;j<levelMap.getW();j++){
//                        System.out.print(itemMap[i][j]);
//                    }
//                    System.out.println();
//                }

            }
        };
        timer.start();


        Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getImage());
        entities.add(bomberman);
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                bomberman.handleKeyReleased(keyEvent.getCode());
            }
        });
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                bomberman.handleKeyPress(keyEvent.getCode());
            }
        });
    }
    public void displayBombCount(){
        BombCount.setTextFill(Color.WHITE);
        BombCount.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,24));
        BombCount.setLayoutY(0);
        BombCount.setPadding(Insets.EMPTY);
        BombCount.setText("Bomb: " + bombCount);

    }
    public void displayScore(){

        Score.setTextFill(Color.WHITE);
        Score.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,24));
        Score.setText("SCORE: " + score);
    }
    public void doTime(HBox layout){
        Label TimerDisplay = new Label();
        TimerDisplay.setTextFill(Color.WHITE);
        TimerDisplay.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 24));
        TimerDisplay.setText("TIME: " + seconds.toString());
        layout.getChildren().add(TimerDisplay);
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        if(time!=null){
            time.stop();
        }
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                TimerDisplay.setText("TIME: "+seconds.toString());
                seconds--;
                if(seconds<=0){
                    time.stop();
                }
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }
    //khong dung ham nay, doc file config txt
    public void createMap() {
        //level de test, can them level
        level =1;
        String fileLink=null;
        if(level == 1){
            fileLink="res/levels/level1.txt";
        }

        levelMap.loadMap(fileLink);
        WIDTH=levelMap.getW();
        HEIGHT=levelMap.getH();
        for(int i=0;i<levelMap.getH();i++) {
            for (int j = 0; j < levelMap.getW(); j++) {
                Grass grass = new Grass(j,i,Sprite.grass.getImage());
                stillObjects.add(grass);
            }
        }
        for(int i=0;i<levelMap.getH();i++){
            for(int j=0;j<levelMap.getW();j++){
                //System.out.print(levelMap.getCharacter(i,j));
                char chr = levelMap.getCharacter(i,j);
                switch (chr){
                    case '*':
                        Brick brick = new Brick(j,i,Sprite.brick.getFxImage());
                        entities.add(brick);
                        break;
                    case '#':
                        Wall wall = new Wall(j,i,Sprite.wall.getFxImage());
                        stillObjects.add(wall);
                        break;
                    case 'x':
                        Portal portal = new Portal(j,i,Sprite.brick.getFxImage());
                        entities.add(portal);
                        break;
                    case 'b':
                        itemBomb bombItem = new itemBomb(j,i,Sprite.brick.getFxImage());
                        entities.add(bombItem);
                        break;
                    case 's':
                        itemSpeed speedItem = new itemSpeed(j,i,Sprite.brick.getImage());
                        entities.add(speedItem);
                        break;
                    case 'f':
                        itemFlame flameItem = new itemFlame(j,i,Sprite.brick.getImage());
                        entities.add(flameItem);
                        break;
                    default:
                        Grass grass = new Grass(j,i,Sprite.grass.getFxImage());
                        stillObjects.add(grass);
                        break;
                }
            }
        }
        for(int i=0;i<levelMap.getH();i++) {
            for (int j = 0; j < levelMap.getW(); j++) {
                //System.out.print(levelMap.getCharacter(i,j));
                char chr = levelMap.getCharacter(i, j);
                switch (chr) {
                    case '1':
                        Balloon balloon = new Balloon(j,i,Sprite.balloom_left1.getImage());
                        entities.add(balloon);
                        break;
                    case '2':
                        Oneal oneal = new Oneal(j,i,Sprite.oneal_left1.getImage());
                        entities.add(oneal);

                }
            }
        }
    }


    public void update() {
        displayScore();
        displayBombCount();
        for(int i=0;i<entities.size();i++){
            entities.get(i).update();
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}

