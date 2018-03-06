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
        showText("Score: 0", 750, 50);
        showText("Reserve Energy: 1", 18, 25, 700);
        showText("Weapon Energy: 2", 18, 25,650);
        showText("Movement Energy: 2", 18, 25, 675);
        //AsteroidsServer.gameServer.send("text: score Score:_0");
        //AsteroidsServer.gameServer.send("text: weapon Weapon_Energy:_2");
        //AsteroidsServer.gameServer.send("text: movement Movement_Energy:_2");
        //AsteroidsServer.gameServer.send("text: reserve Reserve_Energy:_1");
        for (int j = 0; j < ships; j++) {
            addObject(new ShipActor("rsrc/brady_old.png"), j * 100 + 1024 / 2, j * 100 + 768 / 2);

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


