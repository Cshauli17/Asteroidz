package client;

import mayflower.MayflowerImage;
import server.TickingActor;

import java.util.UUID;

public class SpaceObject extends TickingActor {

    public int speed;
    public String image;
    public UUID uuid;

    public SpaceObject(String file, int speed, int rotation){
        if(file != null) {
            MayflowerImage img = new MayflowerImage(file);
            setImage(img);
        }

        image = file;
        this.speed = speed;
        this.uuid = UUID.randomUUID();
        
        setRotation(rotation);
    }

    public void changeSpeed(int change) {
        speed += change;
    }

    public void changeDirection(int change) {
        setRotation(getRotation() + change);
    }

    @Override
    public void act() {

    }

    @Override
    public void tick() {
        move(speed/2.0);
    }
}

