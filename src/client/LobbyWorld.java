package client;

import mayflower.Mayflower;
import mayflower.World;
import server.GameWorld;

public class LobbyWorld extends World {

    public LobbyWorld() {
        setBackground("rsrc/finals/usbank.png");
    }

    @Override
    public void act() {
        if(Mayflower.mousePressed(this)) {
            Main.client.start();
        }
    }
}

