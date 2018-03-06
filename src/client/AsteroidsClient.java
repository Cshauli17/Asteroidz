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

        if(!cmd.equals("clear"))
            System.out.println("Processing: " + s);

        switch (cmd) {
            case "start": {
                wld = new LocalGameWorld();
                Mayflower.setWorld(wld);
                break;
            }
            case "clear": {
                wld.getObjects().removeIf(n -> n instanceof PuppetObject);
                break;
            }
            case "image": { //image: [abc] [speed] [rotation] [x] [y]
                String image = split[1];
                int speed = parseInt(split[2]);
                int rotation = parseInt(split[3]);
                int x = parseInt(split[4]);
                int y = parseInt(split[5]);
                wld.addObject(new PuppetObject(image, speed, rotation), x, y);
                break;
            }
            //ship
            case "ship:": {
                int x = parseInt(split[1]);
                int y = parseInt(split[2]);
                int rotation = parseInt(split[3]);
                int velocity = parseInt(split[4]);
                SpaceObject ship = new PuppetObject("rsrc/brady_old.png", velocity, rotation);
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
            //text: [score/weapon/movement/reserve] string
            case "text:": {
                String[] split2 = split[2].split("_");
                if(split[1].toLowerCase().equals("score")){
                    wld.showText(split2[0] + " " + split2[1], 750, 50);
                }
                else if(split[1].toLowerCase().equals("weapon")){
                    wld.showText(split2[0] + " " + split2[1] + " " + split2[2],18, 25, 650);
                }
                else if(split[1].toLowerCase().equals("movement")){
                    wld.showText(split2[0] + " " + split2[1] + " " + split2[2],18, 25, 675);
                }
                else if(split[1].toLowerCase().equals("reserve")){
                    wld.showText(split2[0] + " " + split2[1] + " " + split2[2],18, 25, 700);
                }
                else if(split[1].toLowerCase().equals("system")){
                    wld.showText(split2[0] + " " + split2[1], 25,50);
                }
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
