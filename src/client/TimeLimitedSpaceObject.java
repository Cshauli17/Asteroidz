package client;

public abstract class TimeLimitedSpaceObject extends SpaceObject {

    private int lifespan;

    TimeLimitedSpaceObject(String file, int speed, int rotation) {
        super(file, speed, rotation);
    }

    @Override
    public void tick() {
        super.tick();

        if(lifespan == 100 && isAtEdge()) {
            this.destroy();
        }

        lifespan++;
        if(lifespan > 100) lifespan = 100;
    }

    boolean isFirstTick() {
        return lifespan == 1;
    }
}
