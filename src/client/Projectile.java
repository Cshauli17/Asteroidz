package client;

public class Projectile extends SpaceObject {

    private int tx, ty;
    private int lifespan;

    public Projectile(int speed, int tx, int ty) {
        super("rsrc/finals/football.png", speed, 0);
        this.tx = tx;
        this.ty = ty;
    }

    @Override
    public void tick() {
        super.tick();

        if(lifespan == 0) {
            turnTowards(tx, ty);
        } else {
            this.getImage().setTransparency(lifespan);
            if(lifespan == 100)
                this.destroy();
        }

        Asteroid other = getOneIntersectingObject(Asteroid.class);

        if(other != null) {
            other.destroy();
            this.destroy();
        }

        lifespan++;
    }
}

class Asteroid extends SpaceObject {

    public boolean large;

    public Asteroid(int speed, int rotation, boolean large) {
        super("rsrc/finals/asteroid" + (large ? 1 : 0) +".png", speed, rotation);
    }

    @Override
    public void destroy() {
        super.destroy();
        if(large) {
            if(Math.random() > 0.33333)
                getWorld().addObject(new Asteroid(speed, getRotation(), false), getCenterX() + 40, getCenterY());
            if(Math.random() > 0.33333)
                getWorld().addObject(new Asteroid(speed, getRotation(), false), getCenterX() - 40, getCenterY());
            if(Math.random() > 0.33333)
                getWorld().addObject(new Asteroid(speed, getRotation(), false), getCenterX(), getCenterY() + 100);
        }
    }
}