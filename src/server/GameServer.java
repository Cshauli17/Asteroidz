package server;

import client.ShipActor;
import client.SpaceObject;
import mayflower.Keyboard;
import mayflower.Mayflower;
import mayflower.MayflowerHeadless;
import mayflower.World;
import mayflower.net.Server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class GameServer extends Server {

    public Mayflower mayflower;
    public List<Player> players;

    public GameServer(Mayflower mayflower) {
        super(21500); //SpaceX is worth 21.5bn

        this.mayflower = mayflower;
        players = new ArrayList<>();
    }

    @Override
    public void process(int i, String s) {
        String[] split = s.split(" ");
        String cmd = split[0].toLowerCase();

        switch (cmd) {
            case "start" : {
                send("start");
                mayflower._setWorld(new GameWorld());
                break;
            }
            // ship:accelerate id
            case "ship:accelerate":{
                // if presses up move player with this id forward
                getPlayer(parseInt(split[1])).ship.changeSpeed(1);
            }
            //ship:slowdown id
            case "ship:slowdown":{
                getPlayer(parseInt(split[1])).ship.changeSpeed(-1);
            }
            case "ship:turnLeft":{
                getPlayer(parseInt(split[1])).ship.changeDirection(1);
            }
            case "ship:turnRight":{
                getPlayer(parseInt(split[1])).ship.changeDirection(-1);
            }

        }
    }

    @Override
    public void onJoin(int i) {
        players.add(new Player(i));
        System.out.println("Player connected " + i);
    }

    @Override
    public void onExit(int i) {
        players.remove(getPlayer(i));
        System.out.println("Player disconnected " + i);
    }

    public Player getPlayer(int i) {
        return players.stream().filter(n -> n.id == i).findFirst().orElse(null);
    }

    //public <T extends SpaceObject> List<T> getObjects(Class<T> clazz) {
    //    if(Mayflower.getWorld()== null) return new ArrayList<>();
    //    return world.getObjects().stream()
    //            .filter(clazz::isInstance)
    //            .map(clazz::cast)
    //            .collect(Collectors.toList());
    //}
}

class Player {

    public int id;
    public int controls;
    public ShipActor ship;

    public Player(int id) {
        this.id = id;
    }

    public boolean hasControls(int... control) {
        for (int i = 0; i < control.length; i++) {
            if((controls & control[i]) != control[i])
                return false;
        }
        return true;
    }
}

class Controls {

    public static final int MOVEMENT    = 1; //001
    public static final int WEAPONS     = 2; //010
    public static final int ENGINEERING = 4; //100

    public static final int ALL         = 7; //111
}