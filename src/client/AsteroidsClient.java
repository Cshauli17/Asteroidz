package client;

import mayflower.net.Client;

public class AsteroidsClient extends Client {

    public AsteroidsClient() {
        this("");
    }

    public AsteroidsClient(String ip) {
        if(ip.equals("")) connect(21500);
        else connect(ip, 21500);
    }

    @Override
    public void process(String s) {

    }

    @Override
    public void onDisconnect(String s) {

    }

    @Override
    public void onConnect() {

    }
}
