package server;

import client.ShipActor;
import mayflower.World;

public class GameWorld extends World {

    public GameWorld(ShipActor ship) {
        setBackground("rsrc/finals/field.png");

        showText("Score: 0", 750, 50);
        showText("Reserve Energy: 1", 18, 25, 700);
        showText("Weapon Energy: 2", 18, 25,650);
        showText("Movement Energy: 2", 18, 25, 675);

        addObject(ship, 1024 / 2, 768 / 2);
    }

    @Override
    public void act() {

    }
}


