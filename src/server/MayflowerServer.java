package server;

import mayflower.*;

public class MayflowerServer extends Mayflower {

    public GameServer server;

    public MayflowerServer() {
        super("Asteroidz Server", 1024, 768);
    }

    @Override
    public void init() {
        server = new GameServer();
    }
}

