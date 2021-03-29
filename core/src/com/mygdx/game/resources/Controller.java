package com.mygdx.game.resources;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.resources.gameobjects.Player;

public class Controller {
    
    public float spaceTimer;
    public boolean spaceButtonLimiter; 
    public HashMap<String, Boolean> keysPressed = new HashMap<String, Boolean>();

    private static Controller controller = new Controller();

    private Controller(){

        System.out.println("Setting up controls...");

        for(String i : new String[]{"Left", "Right", "Up", "Down", "Space"}){
            keysPressed.put(i, false);
        }

        spaceTimer = 0.0f;
        spaceButtonLimiter = false;

        System.out.println("Controls are all ready.");

    }

    public static Controller getController(){
        return controller;
    }

    public void controlHandler(){
        resetSpaceLimit();
        checkForInputs();
    }

    private void resetSpaceLimit(){

        if(spaceButtonLimiter){
            spaceTimer += Gdx.graphics.getDeltaTime();
        }

        if(spaceTimer >= 0.30){
            spaceButtonLimiter = false;
            spaceTimer = 0.0f;
        }

    } 

    private void checkForInputs(){

        if(Gdx.input.isKeyPressed(Keys.ESCAPE)) Gdx.app.exit();
        if(Gdx.input.isKeyPressed(Keys.A)) keysPressed.put("Left", true); else keysPressed.put("Left", false);
        if(Gdx.input.isKeyPressed(Keys.D)) keysPressed.put("Right", true); else keysPressed.put("Right", false);
        if(Gdx.input.isKeyPressed(Keys.W)) keysPressed.put("Up", true); else keysPressed.put("Up", false);
        if(Gdx.input.isKeyPressed(Keys.S)) keysPressed.put("Down", true); else keysPressed.put("Down", false);

        /*
        ! Shoot button limiter
        * Prevents the player from holding the down the key and firing infinite bullets.  This is to
        * game balance as well as manage resources.
        */
        if(Gdx.input.isKeyJustPressed(Keys.SPACE) && !spaceButtonLimiter){
            keysPressed.put("Space", true);
            spaceButtonLimiter = true;
        }
        else{
            keysPressed.put("Space", false);
        }

        /*
        ! Debug Controls  
        */
        if(Gdx.input.isKeyJustPressed(Keys.H)){
            Player.getPlayer().showHitbox = !Player.getPlayer().showHitbox;
        }

    }

}
