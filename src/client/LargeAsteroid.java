package client;

import mayflower.Actor;
import mayflower.MayflowerImage;

public class LargeAsteroid extends Asteroid {
    public LargeAsteroid(int x, int y, int speed, int direction){
        super(x,y,speed,direction);
        MayflowerImage img = new MayflowerImage("rsrc/LargeAsteroid.png");
        setImage(img);
    }

    public boolean isTouching(){
        if(getIntersectingObjects(Actor.class).size() > 0){
                return true;
            }
            return false;

    }


}
