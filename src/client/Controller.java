package client;
import java.lang.reflect.Array;
import mayflower.Keyboard;
import mayflower.Mayflower;

public class Controller extends Mayflower{
    public int[] keys = new int[4];
    public Controller()
    {
        super("Client", 1024, 768);
    }
    @Override
    public void init(){
        keys[0] = (Keyboard.KEY_W);
        keys[1] = (Keyboard.KEY_A);
        keys[2] = (Keyboard.KEY_S);
        keys[3] = (Keyboard.KEY_D);

    }
}
