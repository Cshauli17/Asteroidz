package client;

import mayflower.Actor;

public class Systems extends Actor{
    public static int systemsEnergy;
    private int energy;
    private int reserves;
    public Systems(){
        reserves = 1;
    }

    public void addEnergy(){
        if(reserves > 0){
            energy++;
            reserves--;
        }

    }
    public int getEnergy(){
        return energy;
    }
    public int getReserves(){
        return reserves;
    }
    public void removeEnergy(){
        if(energy > 0){
            energy--;
            reserves++;
        }

    }

    public void act(){
        systemsEnergy = MovementSystem.menergy + WeaponsSystem.wenergy;
        reserves = 5 - systemsEnergy;
    }


}
