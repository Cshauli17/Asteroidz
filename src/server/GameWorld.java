package server;

import client.Asteroid;
import client.AsteroidsClient;
import client.Main;
import client.ShipActor;
import mayflower.World;
import mayflower.*;

public class GameWorld extends World {

    //The game starts when this world is initialized.
    public GameWorld() {
        setBackground("rsrc/finals/field.png");
        int ships = (int) Math.ceil(AsteroidsServer.gameServer.players.size() / 3f);
        int shipCounter = 0;

        for (int j = 0; j < ships; j++) {
            addObject(new ShipActor("rsrc/Spaceship.png"), j * 100 + 1024 / 2, j * 100 + 768 / 2);

            AsteroidsServer.gameServer.send("ship: " + (j * 100 + 1024 / 2) +" "+(j* 100 + 768 / 2)+" 0 0");
        }

        int k = 0;
        for (Player p : AsteroidsServer.gameServer.players) {
            if(k == 3) {
                k = 0;
                shipCounter++;
            }

            p.ship = getObjects(ShipActor.class).get(shipCounter);

            k++;
        }
    }

    @Override
    public void act() {

    }
}


