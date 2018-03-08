package client;

public abstract class TimeLimitedSpaceObject extends SpaceObject {

    private int lifespan;

    TimeLimitedSpaceObject(String file, int speed, int rotation) {
        super(file, speed, rotation);
    }

    @Override
    public void tick() {
        super.tick();

        this.getImage().setTransparency(lifespan);
        if(lifespan >= 250)
            this.destroy();

        lifespan++;
    }

    boolean isFirstTick() {
        return lifespan == 1;
    }
}
