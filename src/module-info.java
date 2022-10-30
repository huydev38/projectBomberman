module uet.oop.bomberman {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;

    opens uet.oop.bomberman to javafx.fxml;
    exports uet.oop.bomberman;
    exports uet.oop.bomberman.sound;
    opens uet.oop.bomberman.sound to javafx.fxml;
    exports uet.oop.bomberman.map;
    opens uet.oop.bomberman.map to javafx.fxml;
    exports uet.oop.bomberman.player;
    opens uet.oop.bomberman.player to javafx.fxml;
}