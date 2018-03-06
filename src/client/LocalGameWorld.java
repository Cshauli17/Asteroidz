package client;

import mayflower.Keyboard;
import mayflower.Mayflower;
import mayflower.World;
import server.GameServer;

public class LocalGameWorld extends World {

    public LocalGameWorld() {
        setBackground("rsrc/finals/field.png");


    }

    @Override
    public void act() {
        if(Mayflower.isKeyDown(Keyboard.KEY_W)) Main.client.send("ship:speed +");
        if(Mayflower.isKeyDown(Keyboard.KEY_S)) Main.client.send("ship:speed -");
        if(Mayflower.isKeyDown(Keyboard.KEY_A)) Main.client.send("ship:turn L");
        if(Mayflower.isKeyDown(Keyboard.KEY_D)) Main.client.send("ship:turn R");

        if(Mayflower.isKeyDown(Keyboard.KEY_LEFT)) Main.client.send("weapon:turn L");
        if(Mayflower.isKeyDown(Keyboard.KEY_RIGHT)) Main.client.send("weapon:turn R");
        if(Mayflower.isKeyDown(Keyboard.KEY_UP)) Main.client.send("weapon:fire");

        if(Mayflower.isKeyDown(Keyboard.KEY_R)) Main.client.send("engineering:add movement");
        if(Mayflower.isKeyDown(Keyboard.KEY_F)) Main.client.send("engineering:remove movement");
        if(Mayflower.isKeyDown(Keyboard.KEY_T)) Main.client.send("engineering:add weapons");
        if(Mayflower.isKeyDown(Keyboard.KEY_G)) Main.client.send("engineering:remove weapons");
    }
}
