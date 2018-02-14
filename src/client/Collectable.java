package client;

import mayflower.Actor;
import mayflower.MayflowerImage;

public class Collectable extends Actor {
    private boolean collected;
    public Collectable(){
        MayflowerImage img = new MayflowerImage("rsrc/Collectable");

    }
    public boolean isTouching(){
        if(getIntersectingObjects(Actor.class).size() > 0){
            return true;
        }
        return false;
    }


    public void act(){
        
    }

}
