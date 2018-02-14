package client;

import mayflower.Actor;

public class SpaceObject extends Actor{

    public int speed;
    public int direction;
    public int locationX;
    public int locationY;

    public SpaceObject(int x, int y, int intSpeed, int intDirection){
        locationX = x;
        locationY = y;
        speed = intSpeed;
        direction = intDirection;
    }

    public int getX(){return locationX;}
    public int getY(){return locationY;}

    public void changeSpeed(int change){
        speed -= change;
    }

    public void changeDirection(int change){
        direction -=change;

    }
    public int GetDirection(){
        return direction;
    }
    @Override
    public void act(){


    }

}
