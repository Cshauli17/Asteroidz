package server;

import mayflower.Mayflower;

public class Main extends Mayflower {

    public static GameServer gameServer;

    private Main() {
        super("Asteroidz Server", 1024, 768);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void init() {
        System.out.println("Starting server...");
        gameServer = new GameServer();
    }
}

