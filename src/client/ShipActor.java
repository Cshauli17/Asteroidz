package client;

import mayflower.Keyboard;
import mayflower.Mayflower;

public class ShipActor extends SpaceObject {

    public ShipActor(String file) {
        super(file, 0 , 0);

    }

    @Override
    public void act(){

    }

    @Override
    public void tick() {
        if(Mayflower.isKeyPressed(Keyboard.KEY_W)){
            Main.client.send("ship:speedup");
        }
        else if(Mayflower.isKeyPressed(Keyboard.KEY_S)){
            Main.client.send("ship:slowdown");
        }
        if(Mayflower.isKeyPressed(Keyboard.KEY_A)){
            Main.client.send("ship:turnleft");
        }
        else if(Mayflower.isKeyPressed(Keyboard.KEY_D)){
            Main.client.send("ship:turnright");
        }
    }

}
