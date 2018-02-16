package server;

import mayflower.World;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TickingWorld extends World {

    private Timer t;
    private long time0, time1;

    public TickingWorld() {

        t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                stick();
            }
        }, 75);
    }

    public void stick() {
        tick();

        if(time0 == 0 || time1 == 0) {
            time0 = System.currentTimeMillis();
            time1 = System.currentTimeMillis();
        }

        time0 = time1;
        time1 = System.currentTimeMillis();

        long diff = Math.abs(time1 - time0);
        long excess = diff - 75;

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                stick();
            }
        }, excess > 1000 || excess < 1 ? 75 : 75 - excess);
    }

    public void tick()
    {
        new ArrayList<>(getObjects()).forEach(n -> {
            if (n instanceof TickingActor) ((TickingActor) n).tick();
        });
    }

    @Override
    public void act() {

    }
}
