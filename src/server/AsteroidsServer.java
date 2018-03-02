package server;

import mayflower.Mayflower;

public class AsteroidsServer extends Mayflower {

    public static GameServer gameServer;

    public AsteroidsServer() {
        super("Asteroidz Server", 1024, 768);
    }

    @Override
    public void init() {
        System.out.println("Starting server...");
        gameServer = new GameServer(this);
    }
}

