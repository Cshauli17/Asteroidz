package server;

import client.ShipActor;
import mayflower.World;

public class GameWorld extends World {

    public GameWorld(ShipActor ship) {
        setBackground("rsrc/finals/field.png");
        addObject(ship, 1024 / 2, 768 / 2);
    }

    @Override
    public void act() {

    }
}