package client;

public class Projectile extends TimeLimitedSpaceObject {

    private int tx, ty;

    public Projectile(int speed, int tx, int ty) {
        super("rsrc/finals/football.png", speed, 0);
        this.tx = tx;
        this.ty = ty;
    }

    @Override
    public void tick() {
        super.tick();

        if(this.isFirstTick()) {
            turnTowards(tx, ty);
        }

        Asteroid other = getOneIntersectingObject(Asteroid.class);

        if(other != null) {
            other.destroy();
            this.destroy();
        }
    }
}

