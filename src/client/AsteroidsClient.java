package client;

import mayflower.Mayflower;
import mayflower.World;
import mayflower.net.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.lang.Integer.parseInt;

public class AsteroidsClient extends Client {

    private World wld;
    public List<String> texts;

    public AsteroidsClient() {
        this("");
    }

    public AsteroidsClient(String ip) {
        if(ip.equals("")) connect(21500);
        else connect(ip, 21500);

        texts = new ArrayList<>();
    }

    public void start() {
        Main.client.send("start");
    }

    @Override
    public void process(String s) {
        String[] split = s.split(" ");
        String cmd = split[0].toLowerCase();

        //if(!cmd.equals("clear"))
        //    System.out.println("Processing: " + s);

        switch (cmd) {
            case "start": {
                wld = new LocalGameWorld();
                Mayflower.setWorld(wld);
                break;
            }
            case "remove": { //remove [uuid]
                UUID uuid = UUID.fromString(split[1]);
                wld.getObjects().removeIf(n -> n instanceof PuppetActor && ((PuppetActor) n).uuid.equals(uuid));
                break;
            }
            case "image": { //image [abc] [uuid] [rotation] [x] [y] [transparency]
                String image = split[1];
                UUID uuid = UUID.fromString(split[2]);
                int rotation = parseInt(split[3]);
                int x = parseInt(split[4]);
                int y = parseInt(split[5]);
                int transparency = parseInt(split[6]);

                PuppetActor actor = wld.getObjects(PuppetActor.class).stream().filter(n -> n.uuid.equals(uuid)).findFirst().orElse(null);

                //Create a new actor for this uuid if one doesnt exist
                if(actor == null) {
                    wld.addObject(new PuppetActor(uuid, image, rotation, x, y), x, y);
                } else { //Otherwise, update the existing
                    actor.setRotation(rotation);
                    actor.setLocation(x, y);
                    actor.getImage().setTransparency(transparency);
                }

                break;
            }
            case "text": { //text [x]|[y]|[size]|[string_underscore_separated] (repeat section for each string to display)

                //String[] split2 = split[2].split("_");
                //if(split[1].toLowerCase().equals("score")){
                //    wld.showText(split2[0] + " " + split2[1], 750, 50);
                //}
                //else if(split[1].toLowerCase().equals("weapon")){
                //    wld.showText(split2[0] + " " + split2[1] + " " + split2[2],18, 25, 650);
                //}
                //else if(split[1].toLowerCase().equals("movement")){
                //    wld.showText(split2[0] + " " + split2[1] + " " + split2[2],18, 25, 675);
                //}
                //else if(split[1].toLowerCase().equals("reserve")){
                //    wld.showText(split2[0] + " " + split2[1] + " " + split2[2],18, 25, 700);
                //}
                //else if(split[1].toLowerCase().equals("system")){
                //    wld.showText(split2[0] + " " + split2[1], 25,50);
                //}

                texts.clear();
                for (int i = 1; i < split.length; i++) {
                    texts.add(split[i]);
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
