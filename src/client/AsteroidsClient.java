package client;

import mayflower.Mayflower;
import mayflower.World;
import mayflower.net.Client;

import static java.lang.Integer.parseInt;

public class AsteroidsClient extends Client {
    private World wld;

    public AsteroidsClient() {
        this("");
    }

    public AsteroidsClient(String ip) {
        if(ip.equals("")) connect(21500);
        else connect(ip, 21500);
    }

    public void start() {
        Main.client.send("start");
    }

    @Override
    public void process(String s) {
        String[] split = s.split(" ");
        String cmd = split[0].toLowerCase();
        System.out.println("Processing: " + s);

        switch (cmd) {
            case "start": {
                wld = new LocalGameWorld();
                Mayflower.setWorld(wld);
                break;
            }
            //ship
            case "ship:": {
                int x = parseInt(split[1]);
                int y = parseInt(split[2]);
                int rotation = parseInt(split[3]);
                int velocity = parseInt(split[4]);
                SpaceObject ship = new PuppetObject("rsrc/Spaceship.png", velocity, rotation);
                wld.addObject(ship,x,y);

                break;
            }
            //asteroid: size x y rotation velocity
            case "asteroid:": {
                String size = split[1].toLowerCase();
                int x = parseInt(split[2]);
                int y = parseInt(split[3]);
                int rotation = parseInt(split[4]);
                int velocity = parseInt(split[5]);
                if(size.equals("small")){
                    SpaceObject asteroid = new PuppetObject("SmallAsteroid.png", velocity, rotation);
                }
                else if(size.equals("large")){
                    SpaceObject asteroid = new PuppetObject("LargeAsteroid.png", velocity, rotation);
                }

                break;
            }
            //collectable: x y
            case "collectable:": {
                int x = parseInt(split[1]);
                int y = parseInt(split[2]);
                SpaceObject collectable = new PuppetObject("Collectable.png",0, 0);
                break;
            }
        }
    }

    @Override
    public void onDisconnect(String s) {
        System.out.println("Disconnected: " + s);
    }

    @Override
    public void onConnect() {
        System.out.println("Connected successfully.");
    }
}
