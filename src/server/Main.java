package server;

public class Main {

    public static MayflowerServer mayflower;
    public static GameServer server;

    public static void main(String[] args) {

        System.out.println(" *** Asteroidz Server *** ");

        mayflower = new MayflowerServer();
        server = mayflower.server;
    }
}

