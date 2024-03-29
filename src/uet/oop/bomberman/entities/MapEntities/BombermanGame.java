package uet.oop.bomberman.entities.MapEntities;

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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Item.Portal;
import uet.oop.bomberman.entities.Item.itemBomb;
import uet.oop.bomberman.entities.Item.itemFlame;
import uet.oop.bomberman.entities.Item.itemSpeed;
import uet.oop.bomberman.entities.MovingEntities.Balloon;
import uet.oop.bomberman.entities.MovingEntities.Bomber;
import uet.oop.bomberman.entities.MovingEntities.Minvo;
import uet.oop.bomberman.entities.MovingEntities.Oneal;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.map.Map;
import uet.oop.bomberman.player.Player;
import uet.oop.bomberman.player.PlayerManagement;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.sound.SoundEffect;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;

public class BombermanGame extends Application {

    Stage window;
    Scene sceneMenu;
    Scene sceneEndGame;
    Scene sceneInstruction;
    Scene sceneRanking;
    Scene getPlayerName;
    Scene sceneLevel;
    int z =0;
    private final int startTime = 300;
    private Integer seconds;
    AnimationTimer timer;

    Timeline time;
    public static boolean endGame = false;
    public int WIDTH = 50;
    public int HEIGHT = 50;
    public static int enemyCount = 0;

    public static int score = 0;
    public static int bombCount = 1;
    public int level = 1;

    private GraphicsContext gc;
    private Canvas canvas;
    private Player player;
    private static final List<Entity> entities = new ArrayList<>();
    private static final List<Entity> stillObjects = new ArrayList<>();

    public static void removeEntities(Entity e) {
        entities.remove(e);
    }

    public static void addEntities(Entity e) {
        entities.add(e);
    }

    public static List<Entity> getEntities() {
        return entities;
    }

    public static char[][] mapMatrix;
    public static int[][] MovableMap;
    public static char[][] itemMap;
    public static boolean isAlive = true;
    Label Score = new Label();
    Label BombCount = new Label();
    Label dLevel = new Label();
    public static Sound sound;
    public static SoundEffect se;
    Group root = new Group();
    public static boolean warrior = true;


    @Override
    public void start(Stage stage) {

        window = stage;
        window.setTitle("BomberMan");
        Button buttonPlay = new Button("Play");
        Button buttonRanking = new Button("Ranking Board");
        Button buttonInstruction = new Button("Instruction");
        StackPane layoutMenu = new StackPane();
        sceneMenu = new Scene(layoutMenu, 1200, 800);

        //Menu
        Image image = new Image(getClass().getResourceAsStream("/menu/menu.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitHeight(800);
        imageView.setFitWidth(1500);
        imageView.setPreserveRatio(true);

        window.setScene(sceneMenu);
        layoutMenu.getChildren().addAll(imageView, buttonPlay, buttonRanking, buttonInstruction);
        buttonPlay.setTranslateY(170);
        buttonRanking.setTranslateY(210);
        buttonInstruction.setTranslateY(250);

        buttonRanking.setFont(Font.font("Comic Sans MS"));
        buttonInstruction.setFont(Font.font("Comic Sans MS"));



        buttonInstruction.setMaxWidth(200);
        buttonInstruction.setMaxHeight(30);
        buttonPlay.setMaxHeight(30);
        buttonRanking.setMaxHeight(30);
        buttonRanking.setMaxWidth(200);
        buttonPlay.setMaxWidth(200);
        buttonPlay.setOnAction(event -> {
//            startGame(level);
            initPlayerSession();
        });
        buttonRanking.setOnAction(event -> {
            try {
                displayRanking();
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        });
        buttonInstruction.setOnAction(event -> {
            displayInstruction();
        });
        window.show();
        //Init sceneIntroLevel
    }

    public void displayRanking() throws FileNotFoundException {
        PlayerManagement.getTop();
        String[] listRank = new String[5];
        for(int i=0;i<5;i++){
            listRank[i]=PlayerManagement.topPlayer.get(i).toString();
        }
        VBox scoreBoard = new VBox();
//        Image image = new Image(getClass().getResourceAsStream("/menu/menu.png"));
//        ImageView imageView = new ImageView(image);
//        imageView.setX(0);
//        imageView.setY(0);
//        imageView.setFitHeight(800);
//        imageView.setFitWidth(1500);
//        imageView.setPreserveRatio(true);

        Image imageBackground = new Image(getClass().getResourceAsStream("/menu/ranking.png"));
        BackgroundImage backgroundImage = new BackgroundImage(imageBackground, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,new BackgroundSize(1,1,true,true,false,false));

        Background background = new Background(backgroundImage);

        scoreBoard.setBackground(background);
        scoreBoard.setOpacity(1);
        Label labelRanking = new Label("LEADER BOARD");
        labelRanking.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,50));
        labelRanking.setTextFill(BLACK);
        scoreBoard.getChildren().add(labelRanking);
        VBox layout = new VBox();
        String s = String.format("%10s", "Name") +
                String.format("%10s", "Score") +
                String.format("%10s", "Time");

        Label label = new Label(s);
        label.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,30));
        label.setTextFill(BLACK);
        layout.getChildren().add(label);
        for(int i=0;i<5;i++){
            Label ranking = new Label((i+1) +listRank[i]);
            ranking.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,30));
            ranking.setTextFill(BLACK);
            layout.getChildren().add(ranking);
        }
        scoreBoard.getChildren().add(layout);
        layout.setTranslateX(380);
        Button backMenu = new Button("Back To Menu");
        backMenu.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,24));
        scoreBoard.getChildren().add(backMenu);
        backMenu.setTranslateY(100);
        backMenu.setOnAction(event -> {
            window.setScene(sceneMenu);
        });
        scoreBoard.setAlignment(Pos.CENTER);
        sceneRanking = new Scene(scoreBoard,1200,800);
        window.setScene(sceneRanking);
    }


    public void initPlayerSession(){
        boolean character1 = true;
        StackPane pane1 = new StackPane();
        AnimationTimer timer2;

        Image image = new Image(getClass().getResourceAsStream("/menu/character1.jpg"));
        AtomicBoolean br = new AtomicBoolean(false);
        ImageView imageView = new ImageView(image);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitHeight(800);
        imageView.setFitWidth(1500);
        imageView.setPreserveRatio(true);
        Button buttonCf = new Button("Warrior");
        Button hunter = new Button("Hunter");
        buttonCf.setTranslateY(170);
        buttonCf.setTranslateX(-270);
        buttonCf.setMaxWidth(200);
        buttonCf.setMaxHeight(30);
        hunter.setTranslateY(170);
        hunter.setTranslateX(270);
        hunter.setMaxWidth(200);
        hunter.setMaxHeight(30);

        pane1.getChildren().addAll(imageView, buttonCf, hunter);
        Scene chooseChar = new Scene(pane1, 1200, 800);
        window.setScene(chooseChar);

        EventHandler<ActionEvent> eventWarrior = event0 -> {
            StackPane pane2 = new StackPane();


            TextField plName = new TextField("Enter your name");

            plName.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 24));
            plName.setMaxWidth(400);
            plName.setAlignment(Pos.CENTER);


            EventHandler<ActionEvent> event = event1 -> {

                player = new Player(plName.getText(), 0, 0);
                warrior = true;
                startGame(level);

            };
            plName.setOnAction(event);
            ImageView imageView1 = new ImageView(new Image(getClass().getResourceAsStream("/menu/ranking.png")));
            imageView1.setX(0);
            imageView1.setY(0);
            imageView1.setFitHeight(800);
            imageView1.setFitWidth(1500);
            imageView1.setPreserveRatio(true);
            pane2.getChildren().addAll(imageView1, plName);


            getPlayerName = new Scene(pane2, 1200, 800);
            window.setScene(getPlayerName);

        };
        buttonCf.setOnAction(eventWarrior);

        EventHandler<ActionEvent> eventHunter = event2 -> {
            StackPane pane2 = new StackPane();


            TextField plName = new TextField("Enter your name");

            plName.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 24));
            plName.setMaxWidth(400);
            plName.setAlignment(Pos.CENTER);


            EventHandler<ActionEvent> event = event1 -> {
                warrior = false;
                player = new Player(plName.getText(), 0, 0);
                startGame(level);

            };
            plName.setOnAction(event);
            ImageView imageView1 = new ImageView(new Image(getClass().getResourceAsStream("/menu/ranking.png")));
            imageView1.setX(0);
            imageView1.setY(0);
            imageView1.setFitHeight(800);
            imageView1.setFitWidth(1500);
            imageView1.setPreserveRatio(true);
            pane2.getChildren().addAll(imageView1, plName);


            getPlayerName = new Scene(pane2, 1200, 800);
            window.setScene(getPlayerName);

        };
        hunter.setOnAction(eventHunter);
    }

    public Image character1()  {
        Image image = new Image(getClass().getResourceAsStream("/menu/character1.png"));
        return image;
    }

    public Image character2()  {
        Image image = new Image(getClass().getResourceAsStream("/menu/character2.png"));
        return image;
    }

    public void displayInstruction() {
        StackPane layoutInstruction = new StackPane();
        sceneInstruction = new Scene(layoutInstruction, 1200, 800);
        //Menu
        Image image = new Image(getClass().getResourceAsStream("/menu/instruction.png"));
        ImageView imageView = new ImageView(image);
        Button backToMenu = new Button("Back To Menu");
        backToMenu.setTranslateY(250);
        backToMenu.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,24));
//        backToMenu.setMaxWidth(200);
//        backToMenu.setMaxHeight(30);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitHeight(800);
        imageView.setFitWidth(1500);
        imageView.setPreserveRatio(true);
        layoutInstruction.getChildren().addAll(imageView, backToMenu);
        window.setScene(sceneInstruction);
        backToMenu.setOnAction(event -> {
            window.setScene(sceneMenu);

        });
    }

    public void startGame(int level) {
        sound = new Sound();
        se = new SoundEffect();
        playMusic();
        seconds=startTime;
        //z=0;
        //displayIntroduceLevel(level);

            Map levelMap = new Map();
            entities.clear();
            stillObjects.clear();
            seconds = startTime;
            root.getChildren().removeAll();
            createMap(levelMap, level);
            endGame = false;
            mapMatrix = levelMap.getMapMatrix();
            MovableMap = levelMap.getMovableMap();
            itemMap = levelMap.getItemMap();
            // Tao Canvas
            canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
            gc = canvas.getGraphicsContext2D();
            // Tao root container
            Group root = new Group();
            root.getChildren().add(canvas);
            HBox layout = new HBox();
            Label TimerDisplay = new Label();

            root.getChildren().add(layout);
            layout.getChildren().add(dLevel);
            layout.getChildren().add(BombCount);
            layout.getChildren().add(TimerDisplay);
            layout.getChildren().add(Score);

            layout.setAlignment(Pos.TOP_CENTER);
            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setSpacing(100);
            layout.setLayoutX(100);
            doTime(TimerDisplay);
            displayScore();
            displayBombCount();
            displayLevel(level);
            // Tao scene
            Scene scene = new Scene(root);

            // Them scene vao stage
            window.setScene(scene);
            window.show();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                    update();
                    render();

//                System.out.println(entities.size());
//      for(int i=0;i< levelMap.getH();i++){
//                  for(int j=0;j<levelMap.getW();j++){
//                      System.out.print(levelMap.getMapMatrix()[i][j]);
//                  }
//                 System.out.println();
//      }

            }
        };
        timer.start();


        Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getImage());
        entities.add(bomberman);
        isAlive = true;
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

    public void displayBombCount() {
        BombCount.setTextFill(BLACK);
        BombCount.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 24));
        BombCount.setLayoutY(0);
        BombCount.setPadding(Insets.EMPTY);
        BombCount.setText("Bomb: " + bombCount);

    }
    public void displayLevel(int level) {
        dLevel.setTextFill(BLACK);
        dLevel.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 24));
        dLevel.setLayoutY(0);
        dLevel.setPadding(Insets.EMPTY);
        dLevel.setText("Level: " + level);

    }

    public void displayScore() {

        Score.setTextFill(BLACK);
        Score.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 24));
        Score.setText("Score: " + score);
    }

    public void displayEndGame() {
        time.stop();
        root.getChildren().removeAll();
        if (level != 3 && isAlive) {
            level += 1;
            player.setTime(player.getTime() + (300 - seconds));
            startGame(level);
        } else if (level==3&&isAlive) {
            player.setTime(player.getTime() + (300 - seconds));
            player.setScore(score);
            player.WriteToFile();
            score = 0;
            StackPane layoutEndGame = new StackPane();
            sceneEndGame = new Scene(layoutEndGame, 1200, 800);
            Image image = new Image(getClass().getResourceAsStream("/menu/win.png"));
            ImageView imageView = new ImageView(image);
            Button backToMenu = new Button("Back To Menu");
            backToMenu.setTranslateY(250);
            backToMenu.setFont(Font.font("Comic Sans MS"));
            backToMenu.setMaxWidth(200);
            backToMenu.setMaxHeight(30);
            imageView.setX(0);
            imageView.setY(0);
            imageView.setFitHeight(800);
            imageView.setFitWidth(1500);
            imageView.setPreserveRatio(false);
            Label result = new Label("Score: " + player.getScore() + "   " + "Time: " + player.getTime());
            result.setTranslateY(180);
            result.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 24));
            result.setTextFill(WHITE);
            layoutEndGame.getChildren().addAll(imageView, backToMenu, result);
            window.setScene(sceneEndGame);
            window.show();
            backToMenu.setOnAction(event -> {
                window.setScene(sceneMenu);
            });
        } else {
            player.setTime(player.getTime() + (300 - seconds));
            player.setScore(score);
            player.WriteToFile();

            score = 0;
            StackPane layoutEndGame = new StackPane();
            sceneEndGame = new Scene(layoutEndGame, 1200, 800);
            //Menu
            Image image = new Image(getClass().getResourceAsStream("/menu/gameover.png"));
            ImageView imageView = new ImageView(image);
            Button backToMenu = new Button("Back To Menu");
            backToMenu.setTranslateY(250);
            backToMenu.setFont(Font.font("Comic Sans MS"));
            backToMenu.setMaxWidth(200);
            backToMenu.setMaxHeight(30);
            imageView.setX(0);
            imageView.setY(0);
            imageView.setFitHeight(800);
            imageView.setFitWidth(1500);
            imageView.setPreserveRatio(true);
            Label result = new Label("Score: " + player.getScore() + "   " + "Time: " + player.getTime());
            result.setTranslateY(180);
            result.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 24));
            result.setTextFill(WHITE);
            layoutEndGame.getChildren().addAll(imageView, backToMenu, result);
            window.setScene(sceneEndGame);
            window.show();
            backToMenu.setOnAction(event -> {
                window.setScene(sceneMenu);
            });
        }
    }


    public void doTime(Label TimerDisplay) {
        TimerDisplay.setTextFill(BLACK);
        TimerDisplay.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 24));
        TimerDisplay.setText("Time:  " + seconds.toString());
        time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        if (time != null) {
            time.stop();
        }
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TimerDisplay.setText("Time: " + seconds.toString());
                seconds=seconds-1;
                if (seconds <= 0) {
                    time.stop();
                }
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }

    //khong dung ham nay, doc file config txt
    public void createMap(Map levelMap, int level) {
        //level de test, can them level
        String fileLink = null;
        if (level == 1) {
            fileLink = "res/levels/level1.txt";
        } else if (level == 2) {
            fileLink = "res/levels/level2.txt";
        } else if (level == 3) {
            fileLink = "res/levels/level3.txt";
        }

        levelMap.loadMap(fileLink);
        WIDTH = levelMap.getW();
        HEIGHT = levelMap.getH();
        for (int i = 0; i < levelMap.getH(); i++) {
            for (int j = 0; j < levelMap.getW(); j++) {
                Grass grass = new Grass(j, i, Sprite.grass.getImage());
                stillObjects.add(grass);
            }
        }
        for (int i = 0; i < levelMap.getH(); i++) {
            for (int j = 0; j < levelMap.getW(); j++) {
                //System.out.print(levelMap.getCharacter(i,j));
                char chr = levelMap.getCharacter(i, j);
                switch (chr) {
                    case '*':
                        Brick brick = new Brick(j, i, Sprite.brick.getFxImage());
                        entities.add(brick);
                        break;
                    case '#':
                        Wall wall = new Wall(j, i, Sprite.wall.getFxImage());
                        stillObjects.add(wall);
                        break;
                    case 'x':
                        Portal portal = new Portal(j, i, Sprite.brick.getFxImage());
                        entities.add(portal);
                        break;
                    case 'b':
                        itemBomb bombItem = new itemBomb(j, i, Sprite.brick.getFxImage());
                        entities.add(bombItem);
                        break;
                    case 's':
                        itemSpeed speedItem = new itemSpeed(j, i, Sprite.brick.getImage());
                        entities.add(speedItem);
                        break;
                    case 'f':
                        itemFlame flameItem = new itemFlame(j, i, Sprite.brick.getImage());
                        entities.add(flameItem);
                        break;
                    default:
                        Grass grass = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grass);
                        break;
                }
            }
        }
        for (int i = 0; i < levelMap.getH(); i++) {
            for (int j = 0; j < levelMap.getW(); j++) {
                //System.out.print(levelMap.getCharacter(i,j));
                char chr = levelMap.getCharacter(i, j);
                switch (chr) {
                    case '1':
                        Balloon balloon = new Balloon(j, i, Sprite.balloom_left1.getImage());
                        entities.add(balloon);
                        enemyCount += 1;
                        break;
                    case '2':
                        Oneal oneal = new Oneal(j, i, Sprite.oneal_left1.getImage());
                        entities.add(oneal);
                        enemyCount += 1;
                        break;
                    case '3':
                        Minvo minvo = new Minvo(j, i, Sprite.minvo_left1.getImage());
                        entities.add(minvo);
                        enemyCount += 1;
                        break;

                }
            }
        }
    }

    public static void playMusic(){
        sound.setFile();
        sound.play();
        sound.loop();
    }
    public static void stopMusic(){
        sound.stop();
    }
    public static void playSE(int i){
        se.setFile(i);
        se.play();
    }

    public void detectGameEnd() {
        if (endGame || seconds == 0) {
            timer.stop();
            stopMusic();
            displayEndGame();
        }
    }


    public void update() {

        displayScore();
        displayBombCount();
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
        detectGameEnd();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}

