package client;

import mayflower.*;

public class Laser extends SpaceObject {

    public Laser(String file, int direction) {

        super(file, 1, direction);

        MayflowerImage img = new MayflowerImage("rsrc/Laser.png");
        setImage(img);
    }

    public boolean isTouching() {

        if (getIntersectingObjects(Actor.class).size() > 0) {
            return true;
        }

        return false;
    }

    @Override
    public void tick() {

    }
}
