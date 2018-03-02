package client;

import mayflower.Mayflower;
import mayflower.World;
import server.AsteroidsServer;
import server.GameWorld;

public class LobbyWorld extends World {

    public LobbyWorld() {
        setBackground("rsrc/finals/usbank.png");
    }

    @Override
    public void act() {
        getTexts().clear();
        showText(AsteroidsServer.gameServer.players.size() + " players", 1024/2, 768/2);

        if(Mayflower.mousePressed(this)) {
            Main.client.start();
        }
    }
}