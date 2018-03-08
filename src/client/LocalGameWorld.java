package client;

import mayflower.Keyboard;
import mayflower.Mayflower;
import mayflower.World;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

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

        int x = Mayflower.getMouseInfo().getX();
        int y = Mayflower.getMouseInfo().getY();
        if(Mayflower.mousePressed(this)) Main.client.send("weapon:fire " + x + " " + y);

        if(Mayflower.isKeyPressed(Keyboard.KEY_R)) Main.client.send("engineering:add movement");
        if(Mayflower.isKeyPressed(Keyboard.KEY_F)) Main.client.send("engineering:remove movement");
        if(Mayflower.isKeyPressed(Keyboard.KEY_T)) Main.client.send("engineering:add weapons");
        if(Mayflower.isKeyPressed(Keyboard.KEY_G)) Main.client.send("engineering:remove weapons");

        if(Mayflower.isKeyPressed(Keyboard.KEY_1)) Main.client.send("system:toggle movement");
        if(Mayflower.isKeyPressed(Keyboard.KEY_2)) Main.client.send("system:toggle weapons");
        if(Mayflower.isKeyPressed(Keyboard.KEY_3)) Main.client.send("system:toggle engineering");

        //Texts
        getTexts().clear();
        new ArrayList<>(Main.client.texts).forEach(n -> {
            String[] str = n.split("\\|");
            showText(str[3].replace("_", " "), parseInt(str[2]), parseInt(str[0]), parseInt(str[1]));
        });
    }
}
