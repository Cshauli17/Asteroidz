package client;

import mayflower.MayflowerImage;
import server.TickingActor;

public abstract class SpaceObject extends TickingActor {

    public int speed;
    public int direction;
    public String image;

    public SpaceObject(String file, int intSpeed, int intDirection){
        if(file != null) {
            MayflowerImage img = new MayflowerImage(file);
            setImage(img);
        }
        image = file;
        speed = intSpeed;
        direction = intDirection;

    }

    public void changeSpeed(int change) {
        speed += change;
    }

    public void changeDirection(int change) {
        direction += change;
    }

    @Override
    public void act(){

    }

    @Override
    public void tick() {
        move(speed/2.0);
        setRotation(direction);
    }
}
