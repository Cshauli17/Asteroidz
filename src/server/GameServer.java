package server;

import client.*;
import mayflower.Mayflower;
import mayflower.World;
import mayflower.net.Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GameServer extends Server {

    public Mayflower mayflower;
    public World world;
    public ShipActor ship;
    public HashMap<Integer, Integer> controls;
    public List<Player> players;
    public Ticker ticker;
    public int score;

    public GameServer(Mayflower mayflower) {
        super(21500); //SpaceX is worth 21.5bn

        this.ticker = new Ticker();
        this.mayflower = mayflower;
        this.players = new ArrayList<>();
        this.controls = new HashMap<>();

        controls.put(Controls.MOVEMENT, 2);
        controls.put(Controls.WEAPONS, 2);
        controls.put(Controls.ENGINEERING, 1);
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
                break;
            }

            case "ship:speed":{ //ship:speed [+|-]
                if(getPlayer(i).hasControls(Controls.MOVEMENT) && ship != null) {
                    int j = controls.get(Controls.MOVEMENT);
                    ship.changeSpeed(split[1].equals("+") ? j : -j);
                }
                break;
            }

            case "ship:turn":{ //ship:turn [L|R]
                if(getPlayer(i).hasControls(Controls.MOVEMENT) && ship != null) {
                    ship.changeDirection(split[1].equals("R") ? 4 : -4);
                }
                break;
            }

            case "weapon:fire":{ //weapon:fire [x] [y]
                if(getPlayer(i).hasControls(Controls.WEAPONS) && ship != null
                        && controls.get(Controls.WEAPONS) > 0) {
                    int x = Integer.parseInt(split[1]);
                    int y = Integer.parseInt(split[2]);
                    Projectile proj = new Projectile(controls.get(Controls.WEAPONS) * 10, x, y);
                    world.addObject(proj, ship.getCenterX(), ship.getCenterY());
                }
                // todo fire cannon. power alters projectile speed. xy is target location
                break;
            }

            case "engineering:add":{ //engineering:add [movement|weapons]
                if(getPlayer(i).hasControls(Controls.ENGINEERING) && world != null
                        && controls.get(Controls.ENGINEERING) > 0) {
                    switch(split[1]) {
                        case "movement": {
                            controls.put(Controls.MOVEMENT, controls.get(Controls.MOVEMENT) + 1);
                            controls.put(Controls.ENGINEERING, controls.get(Controls.ENGINEERING) - 1);
                            break;
                        }
                        case "weapons": {
                            controls.put(Controls.WEAPONS, controls.get(Controls.WEAPONS) + 1);
                            controls.put(Controls.ENGINEERING, controls.get(Controls.ENGINEERING) - 1);
                            break;
                        }
                    }
                }
                break;
            }

            case "engineering:remove":{ //engineering:remove [movement|weapons]
                if(getPlayer(i).hasControls(Controls.ENGINEERING) && world != null) {
                    switch(split[1]) {
                        case "movement": {
                            if(controls.get(Controls.MOVEMENT) > 0) {
                                controls.put(Controls.MOVEMENT, controls.get(Controls.MOVEMENT) - 1);
                                controls.put(Controls.ENGINEERING, controls.get(Controls.ENGINEERING) + 1);
                            }
                            break;
                        }
                        case "weapons": {
                            if(controls.get(Controls.WEAPONS) > 0) {
                                controls.put(Controls.WEAPONS, controls.get(Controls.WEAPONS) - 1);
                                controls.put(Controls.ENGINEERING, controls.get(Controls.ENGINEERING) + 1);
                            }
                            break;
                        }
                    }
                }
                break;
            }

            case "system:toggle":{ //system:toggle [movement|weapons|engineering]
                if(world != null) {
                    switch(split[1]) {
                        case "movement" : {
                            getPlayer(i).controls += getPlayer(i).hasControls(Controls.MOVEMENT) ? -1 : 1;
                            break;
                        }
                        case "weapons" : {
                            getPlayer(i).controls += getPlayer(i).hasControls(Controls.WEAPONS) ? -2 : 2;
                            break;
                        }
                        case "engineering" : {
                            getPlayer(i).controls += getPlayer(i).hasControls(Controls.ENGINEERING) ? -4 : 4;
                            break;
                        }
                    }
                }
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

    public void sendClient(Player p) {
        if(world != null) {
            getObjects(SpaceObject.class).forEach(n -> {
                send(p.id, "image " + n.image + " " + n.uuid + " " + n.getRotation() + " " + n.getX() + " " + n.getY() + " " + n.getImage().getTransparency());
            });

            StringBuilder texts = new StringBuilder();
            texts.append("text ");
            texts.append(getText("Score: " + score, 32, 750, 50));
            texts.append(getText("Movement - " + controls.get(Controls.MOVEMENT), 18, 25, 650));
            texts.append(getText("Weapon - " + controls.get(Controls.WEAPONS), 18, 25, 675));
            texts.append(getText("Reserves - " + controls.get(Controls.ENGINEERING), 18, 25, 700));

            if(p.hasControls(Controls.MOVEMENT))
                texts.append(getText("Movement (WASD)", 14, 25, 25));
            if(p.hasControls(Controls.WEAPONS))
                texts.append(getText("Weapons (Mouse)", 14, 25, 50));
            if(p.hasControls(Controls.ENGINEERING))
                texts.append(getText("Engineering (RFTG)", 14, 25, 75));

            send(p.id, texts.toString().trim());
        }
    }

    private String getText(String text, int size, int x, int y) {
        return x + "|" + y + "|" + size + "|" + text.replace(" ", "_") + " ";
    }

    //Do something interesting. Spawn an asteroid, a collectible, anything, really.
    public void periodicTask() {
        if(world == null) return;

        Random event = new Random();
        int x, y;
        int speed = event.nextInt(15) + 15;
        int rotation = event.nextInt(360);

        if(event.nextBoolean()) {
            x = event.nextInt(Mayflower.getWidth());
            y = event.nextBoolean() ? Mayflower.getHeight() + 50 : -50;
        } else {
            x = event.nextBoolean() ? Mayflower.getWidth() + 50 : -50;
            y = event.nextInt(Mayflower.getHeight());
        }

        //Spawn either an asteroid or a ring
        if(event.nextBoolean()) {
            world.addObject(new Asteroid(speed, rotation, event.nextBoolean()), x, y);
            System.out.println("New asteroid " + x + " " + y);
        } else {
            world.addObject(new Collectible(speed, rotation), x, y);
            System.out.println("New ring " + x + " " + y);
        }
    }
}

class Player {

    public int id;
    public int controls;

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