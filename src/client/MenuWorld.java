package client;

import mayflower.Mayflower;
import mayflower.World;
import mayflower.event.EventListener;
import mayflower.ui.Button;

public class MenuWorld extends World implements EventListener {

    public MenuWorld() {
        setBackground("rsrc/lii.png");

        addObject(new ModeSelectButton(true, this), 0, 0);
        addObject(new ModeSelectButton(false, this), 1024/2, 0);
    }

    @Override
    public void act() {

    }

    @Override
    public void onEvent(String s) {
        if(s.startsWith("start")) {
            if(s.equals("start false")) //new server
            {

            }
            else if(s.equals("start true")) //join server
            {
                String ip = Mayflower.ask("Enter a server IP to join.");
                
            }
        }
    }
}

class ModeSelectButton extends Button {

    public ModeSelectButton(boolean join, EventListener listener) {
        super("rsrc/finals/lii_" + (join ? "brady" : "foles") + "_0.png", "start " + join);
        setHoverImage("rsrc/finals/lii_" + (join ? "brady" : "foles") + "_1.png");
        addEventListener(listener);
    }
}