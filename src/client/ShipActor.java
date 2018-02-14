package client;
import mayflower.Actor;
public class ShipActor extends SpaceObject{


    public ShipActor(int x, int y, int speed, int direction) {
        super(x,y, speed, direction);
        setImage("rsrc/SpaceShip.png");
    }

    @Override
    public void act(){

    }

}
