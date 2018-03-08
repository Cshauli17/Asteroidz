package server;

import mayflower.MayflowerHeadless;

public class AsteroidsServer extends MayflowerHeadless {

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

