package client;

import mayflower.Actor;

public class Systems extends Actor{
    public static int systemsEnergy;
    private int energy;
    private int reserves;
    public Systems(){

    }

    public void addEnergy(){
        if(reserves > 0){
            energy++;
            reserves--;
        }

    }

    public void removeEnergy(){
        if(energy > 0){
            energy--;
            reserves++;
        }

    }

    public void act(){
        systemsEnergy = EngineerSystem.eenergy + MovementSystem.menergy + WeaponsSystem.wenergy;
    }


}
