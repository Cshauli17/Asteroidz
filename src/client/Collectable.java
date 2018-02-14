package client;

import mayflower.Actor;
import mayflower.MayflowerImage;

public class Collectable extends Actor {
    private boolean collected;
    private int x;
    private int y;
    public Collectable(int x1, int y1){
        MayflowerImage img = new MayflowerImage("rsrc/Collectable");
        x = x1;
        y = y1;
    }
    public int getX(){return x;}
    public int getY(){return y;}
    public boolean isTouching(){
        if(getIntersectingObjects(Actor.class).size() > 0){
            return true;
        }
        return false;
    }


    public void act(){
        
    }

}
