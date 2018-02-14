package client;

import mayflower.Actor;
import mayflower.MayflowerImage;

public class LargeAsteroid extends Asteroid {
    public LargeAsteroid(){
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
