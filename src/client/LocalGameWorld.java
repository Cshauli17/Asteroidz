package client;

import mayflower.World;

public class LocalGameWorld extends World {

    public LocalGameWorld() {
        setBackground("rsrc/finals/field.png");
        //addObject(new ShipActor("rsrc/Spaceship.png"), 100, 100);
        //addObject(new Asteroid((int)Math.random()*1 + 5,(int)Math.random()*360,"rsrc/LargeAsteroid.png"),100,100);
    }

    @Override
    public void act() {

    }
}
