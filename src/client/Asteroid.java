package client;


import mayflower.Actor;
import mayflower.MayflowerImage;

public class Asteroid extends SpaceObject {

    public Asteroid(int speed, int direction, String image) {
        super(speed, direction);
        MayflowerImage img = new MayflowerImage(image);
        setImage(img);
    }

    public void act() {

    }

    @Override
    public void tick() {

    }

    public boolean isTouching(){
        if(getIntersectingObjects(Actor.class).size() > 0){
            return true;
        }
        return false;
    }
}
