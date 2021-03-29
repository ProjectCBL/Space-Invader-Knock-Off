package com.mygdx.game.resources;

import java.util.ArrayList;

public class GarbageCollector {
    
    private float bulletLifeLimit = 1.5f;
    private static GarbageCollector garbageCollector = new GarbageCollector();

    private GarbageCollector(){}

    public static GarbageCollector getCollector(){
        return garbageCollector;
    }

    public void clean(){
        cleanBulletsFromScreen();
    }

    private void cleanBulletsFromScreen(){

        ArrayList<Integer> markers = new ArrayList<>();

        for(int i = 0; i < Renderer.aliveBullets.size(); i++){
            if(Renderer.aliveBullets.get(i).lifeSpan >= bulletLifeLimit) markers.add(i); 
        }

        for(int index: markers) Renderer.aliveBullets.remove(Renderer.aliveBullets.get(index));

    }

}
