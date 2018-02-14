package server;

import client.SpaceObject;
import mayflower.net.Server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GameServer extends Server {

    public List<Player> players;
    public List<SpaceObject> objects;

    public GameServer() {
        super(21500); //SpaceX is worth 21.5bn

        players = new ArrayList<>();
    }

    @Override
    public void process(int i, String s) {

    }

    @Override
    public void onJoin(int i) {
        players.add(new Player(i));

        for (Player p : players) {

        }
    }

    @Override
    public void onExit(int i) {
        players.remove(getPlayer(i));
    }

    public Player getPlayer(int i) {
        return players.stream().filter(n -> n.id == i).findFirst().orElse(null);
    }

    public <T extends SpaceObject> List<T> getObjects(Class<T> clazz) {
        return objects.stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .collect(Collectors.toList());
    }
}

class Player {

    public int id;
    public int controls;
    public SpaceObject ship;

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