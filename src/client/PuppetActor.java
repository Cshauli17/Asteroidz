package client;

import mayflower.Actor;
import mayflower.MayflowerImage;

import java.util.UUID;

public class PuppetActor extends Actor {

    public UUID uuid;

    public PuppetActor(UUID uuid, String file, int rotation, int x, int y) {
        if(file != null) {
            MayflowerImage img = new MayflowerImage(file);
            setImage(img);
        }

        this.uuid = uuid;

        setRotation(rotation);
        setLocation(x, y);
    }

    @Override
    public void act() {
    }
}
