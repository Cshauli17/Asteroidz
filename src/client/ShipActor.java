package client;
import mayflower.Actor;
public class ShipActor extends SpaceObject{


    public ShipActor(int speed, int direction) {
        super(speed, direction);
        setImage("rsrc/SpaceShip.png");
    }

    @Override
    public void act(){

    }

}
