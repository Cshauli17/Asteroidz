package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mayflower.Mayflower;

public class Main extends Mayflower {

    private Main() {
        super("Asteroidz", 1024, 768);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void init() {

        setWorld(new MenuWorld());
    }
}
