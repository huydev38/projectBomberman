package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    Stage window;
    Scene sceneMenu;
    public int WIDTH=20;
    public int HEIGHT=20;

    public  int level;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();


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
        sceneMenu = new Scene(layoutMenu, 300,300);
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
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();


        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
    }

    public void createMap() {
        //level de test
        level =1;
        String fileLink=null;
        if(level == 1){
            fileLink="res/levels/level1.txt";
        }
        map levelMap = new map();
        levelMap.loadMap(fileLink);
        WIDTH=levelMap.getW();
        HEIGHT=levelMap.getH();
        for(int i=0;i<levelMap.getH();i++){
            for(int j=0;j<levelMap.getW();j++){
                //System.out.print(levelMap.getCharacter(i,j));
                char chr = levelMap.getCharacter(i,j);
                switch (chr){
                    case '*':
                        Entity brick = new Brick(j,i,Sprite.brick.getFxImage());
                        stillObjects.add(brick);
                        break;
                    case '#':
                        Entity wall = new Wall(j,i,Sprite.wall.getFxImage());
                        stillObjects.add(wall);
                        break;
                    default:
                        Entity grass = new Grass(j,i,Sprite.grass.getFxImage());
                        stillObjects.add(grass);
                        break;
                }
            }
            //System.out.println();
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}

