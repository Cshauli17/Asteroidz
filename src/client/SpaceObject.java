package client;

import server.TickingActor;

public abstract class SpaceObject extends TickingActor {

    public int speed;
    public int direction;

    public SpaceObject(int intSpeed, int intDirection){
        speed = intSpeed;
        direction = intDirection;
    }

    public void changeSpeed(int change){
        speed -= change;
    }

    public void changeDirection(int change){
        direction -= change;
    }

    public int GetDirection(){
        return direction;
    }

    public int getSpeed(){return speed;}

    @Override
    public void act(){

    }

    @Override
    public abstract void tick();
}
