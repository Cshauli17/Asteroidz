package server;

import client.*;
import mayflower.Mayflower;
import mayflower.World;
import mayflower.net.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameServer extends Server {

    public Mayflower mayflower;
    public World world;
    public ShipActor ship;
    public List<Player> players;
    public Ticker ticker;

    public GameServer(Mayflower mayflower) {
        super(21500); //SpaceX is worth 21.5bn

        this.ticker = new Ticker();
        this.mayflower = mayflower;
        this.players = new ArrayList<>();
    }

    @Override
    public void process(int i, String s) {
        String[] split = s.split(" ");
        String cmd = split[0].toLowerCase();
        System.out.println("Server processing: " + s);

        switch (cmd) {
            case "start" : {
                send("start");
                this.ship = new ShipActor();
                this.world = new GameWorld(ship);
                mayflower._setWorld(world);

                //if(getPlayer(i).sys.equals("weapon")){world.showText("Weapons System",25,50);}
                //else if(getPlayer(i).sys.equals("movement")){world.showText("Movement System",25,50);}
                //else if(getPlayer(i).sys.equals("engineer")){world.showText("Engineer System" , 25, 50);}
                //send("text: score Score:_0");
                //send("text: weapon Weapon_Energy:_" + getPlayer(i).weapon.getEnergy());
                //send("text: movement Movement_Energy:_" + getPlayer(i).movement.getEnergy());
                //send("text: reserve Reserve_Energy:_" + getPlayer(i).weapon.getReserves());
                //if(getPlayer(i).sys.equals("weapon")){send(i,"text: system Weapons_System");}
                //else if(getPlayer(i).sys.equals("movement")){send(i,"text: system Movement_System");}
                //else if(getPlayer(i).sys.equals("engineer")){send(i,"text: system Engineer_System");}
                break;
            }

            case "ship:speed":{ //ship:speed [+|-]
                if(getPlayer(i).hasControls(Controls.MOVEMENT) && ship != null)
                    ship.changeSpeed(split[1].equals("+") ? 1 : -1);
                break;
            }

            case "ship:turn":{ //ship:turn [L|R]
                if(getPlayer(i).hasControls(Controls.MOVEMENT) && ship != null)
                    ship.changeDirection(split[1].equals("R") ? 4 : -4);
                break;
            }

            case "weapon:turn":{ //ship:turn [L|R]
                if(getPlayer(i).hasControls(Controls.WEAPONS) && ship != null)
                // todo rotate cannon.
                break;
            }

            case "weapon:fire":{ //ship:fire
                if(getPlayer(i).hasControls(Controls.WEAPONS) && ship != null)
                // todo fire cannon.
                break;
            }

            case "engineering:add":{ //engineering:add [movement|weapons]
                if(getPlayer(i).hasControls(Controls.ENGINEERING) && world != null)
                // todo
                break;
            }

            case "engineering:remove":{ //engineering:add [movement|weapons]
                if(getPlayer(i).hasControls(Controls.ENGINEERING) && world != null)
                // todo
                break;
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

    public <T extends SpaceObject> List<T> getObjects(Class<T> clazz) {
        if(world == null) return new ArrayList<>();
        return world.getObjects().stream()
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .collect(Collectors.toList());
    }

    public void sendClient() {
        if(world != null) {
            send("clear");

            getObjects(SpaceObject.class).forEach(n ->
                    send("image " + n.image + " " + n.speed + " " + n.getRotation() + " " + n.getX() + " " + n.getY()));
        }
    }
}

class Player {

    public int id;
    public int controls;

    public String sys;
    public Systems weapon;
    public Systems movement;
    public Systems engineer;

    public Player(int id) {

        this.id = id;
        this.controls = Controls.ALL;

        ArrayList<String> systemsArrayList = new ArrayList<>();
        weapon = new WeaponsSystem();
        movement = new MovementSystem();
        engineer = new EngineerSystem();
        systemsArrayList.add("engineer");
        systemsArrayList.add("movement");
        systemsArrayList.add("weapon");
        sys = systemsArrayList.get(id%3);

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