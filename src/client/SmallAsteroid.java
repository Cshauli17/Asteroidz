package client;

import mayflower.MayflowerImage;

public class SmallAsteroid extends Asteroid {
    public SmallAsteroid(int x, int y, int speed, int direction){
        super(x,y,speed,direction);
        MayflowerImage img = new MayflowerImage("rsrc/SmallAsteroid.png");
        setImage(img);
    }
}
