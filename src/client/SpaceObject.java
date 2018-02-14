package client;

import mayflower.Actor;

public class SpaceObject extends Actor{

    public int speed;
    public int direction;

    public SpaceObject(int intSpeed, int intDirection){
        speed = intSpeed;
        direction = intDirection;
    }
    public void ChangeSpeed(int change){
        speed -= change;
    }
    public void ChangeDirection(int change){
        direction -=change;
    }
    public int GetDirection(){
        return direction;
    }
    @Override
    public void act(){


    }

}
