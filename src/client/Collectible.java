package client;

import server.AsteroidsServer;

public class Collectible extends TimeLimitedSpaceObject {

    public Collectible(int speed, int rotation) {
        super("rsrc/finals/ring.png", speed, rotation);
    }

    @Override
    public void act() {
        super.act();

        ShipActor other = getOneIntersectingObject(ShipActor.class);

        if(other != null) {
            AsteroidsServer.gameServer.score++;
            this.destroy();
        }
    }
}
