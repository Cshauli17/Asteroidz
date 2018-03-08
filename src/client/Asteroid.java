package client;

import server.AsteroidsServer;

public class Asteroid extends SpaceObject {

    public boolean large;

    public Asteroid(int speed, int rotation, boolean large) {
        super("rsrc/finals/asteroid" + (large ? 1 : 0) +".png", speed, rotation);
        this.large = large;
    }

    @Override
    public void destroy() {
        if(large) {
            if(Math.random() > 0.33333)
                getWorld().addObject(new Asteroid(speed, getRotation(), false), getCenterX() + 40, getCenterY());
            if(Math.random() > 0.33333)
                getWorld().addObject(new Asteroid(speed, getRotation(), false), getCenterX() - 40, getCenterY());
            if(Math.random() > 0.33333)
                getWorld().addObject(new Asteroid(speed, getRotation(), false), getCenterX(), getCenterY() + 100);
        }
        super.destroy();
    }

    @Override
    public void act() {
        super.act();

        ShipActor other = getOneIntersectingObject(ShipActor.class);

        if(other != null) {
            this.destroy();
            AsteroidsServer.gameServer.send("end");
        }
    }
}
