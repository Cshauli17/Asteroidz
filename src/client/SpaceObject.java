package client;

import mayflower.MayflowerImage;
import server.TickingActor;

public abstract class SpaceObject extends TickingActor {

    public int speed;
    public int direction;

    public SpaceObject(String file, int intSpeed, int intDirection){
        MayflowerImage img = new MayflowerImage(file);
        setImage(img);
        speed = intSpeed;
        direction = intDirection;

    }

    public void changeSpeed(int change){
        speed += change;
    }

    public void changeDirection(int change){
        direction += change;
        setRotation(direction);
    }

    public int getSpeed(){return speed;}

    @Override
    public void act(){

    }

    @Override
    public void tick() {
        System.out.println("sup");
        move(getSpeed());
    }
}
