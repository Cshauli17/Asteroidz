package client;

import mayflower.*;

public class Laser extends SpaceObject{
    public Laser(int x, int y, int speed, int direction){
        super(x,y,speed,direction);
        MayflowerImage img = new MayflowerImage("rsrc/Laser.png");
        setImage(img);
    }
    public boolean isTouching() {
        if (getIntersectingObjects(Actor.class).size() > 0) {
            return true;
        }
        return false;

    }

}
