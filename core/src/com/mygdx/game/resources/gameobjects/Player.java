package com.mygdx.game.resources.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.resources.Controller;
import com.mygdx.game.resources.Renderer;
import com.mygdx.game.resources.effects.DeathAnim;

public class Player extends Entity{

    public int health;
    public boolean showHitbox;

    private float dx = 150f;
    private float dy = 150f;
    private float bulletVelocity = 300f;
    private static Player player = new Player();

    private Player(){
        
        System.out.println("Player is getting ready...");

        health = 3;

        isEnabled = true;
        showHitbox = false;

        shapeRenderer = new ShapeRenderer();

        DeathAnim.setShapRenderer(shapeRenderer);

        origin = new Vector2(
            Gdx.graphics.getWidth()/2, 
            Gdx.graphics.getHeight()/2
        );

        updateHitboxLocation();

        shapeColor = Color.WHITE;

        System.out.println("Player is locked and loaded.");

    }

    public static Player getPlayer(){
        return player;
    }

    @Override
    public void show() {

        movePlayer();
        renderHitBox();

        shapeRenderer.begin(ShapeType.Filled);

        renderWholeShip();
        if(health <= 0) die();

        shapeRenderer.end();

    }

    private void movePlayer(){

        if(Controller.getController().keysPressed.get("Left")){
            origin.x -= dx * Gdx.graphics.getDeltaTime();
        }
        if(Controller.getController().keysPressed.get("Right")){
            origin.x += dx * Gdx.graphics.getDeltaTime();
        }
        if(Controller.getController().keysPressed.get("Up")){
            origin.y += dy * Gdx.graphics.getDeltaTime();
        }
        if(Controller.getController().keysPressed.get("Down")){
            origin.y -= dy * Gdx.graphics.getDeltaTime();
        }
        if(Controller.getController().keysPressed.get("Space")){
            shoot();
        }

        updateHitboxLocation();

    }

    private void renderWholeShip(){

        if(isEnabled){
            renderBaseOfShip();
            renderLeftWing();
            renderRightWing();
        }
        
    }

    private void renderHitBox(){

        if(showHitbox){

            shapeRenderer.begin(ShapeType.Line);

            shapeRenderer.setColor(shapeColor);
            shapeRenderer.rect(
                hitBox.x,
                hitBox.y,
                hitBox.width,
                hitBox.height
            );

            shapeRenderer.end();

        }
        
    }

    private void renderBaseOfShip(){

        //Back Piece
        shapeRenderer.setColor(shapeColor);
        shapeRenderer.rect(
            origin.x - 15,
            origin.y - 15 - 15, 
            30, 
            30
        );

        //Blaster/Front Piece
        if(!Controller.getController().spaceButtonLimiter) shapeRenderer.setColor(Color.ORANGE);
        else shapeRenderer.setColor(Color.BROWN);
        shapeRenderer.rect(
            origin.x - 8,
            origin.y + 5 - 35, 
            16, 
            70
        );

        //Decal
        shapeRenderer.setColor(Color.ORANGE);
        shapeRenderer.rect(
            origin.x - 8,
            origin.y - 30, 
            16, 
            60
        );

        //Front
        shapeRenderer.setColor(shapeColor);
        shapeRenderer.rect(
            origin.x - 15,
            origin.y + 15 - 15, 
            30, 
            30
        );

        //Left Fin
        shapeRenderer.setColor(shapeColor);
        shapeRenderer.rect(
            origin.x - 4 - 11,
            origin.y - 32 - 5, 
            8, 
            10
        );

        //Right Fin
        shapeRenderer.setColor(shapeColor);
        shapeRenderer.rect(
            origin.x - 4 + 11,
            origin.y - 32 - 5, 
            8, 
            10
        );

    }

    private void renderLeftWing(){

        //Left Wing
        shapeRenderer.setColor(shapeColor);
        shapeRenderer.rect(
            origin.x - 25 - 25/2, 
            origin.y - 25/2, 
            25, 
            25
        );

        //Wing Tip
        shapeRenderer.setColor(shapeColor);
        shapeRenderer.rect(
            origin.x - 37 - 15/2, 
            origin.y - 15/2-5, 
            15, 
            15
        );

        //Turbine/Extra Gunner
        shapeRenderer.setColor(Color.ORANGE);
        shapeRenderer.rect(
            origin.x - 25 - 15/2, 
            origin.y - 25+4, 
            15, 
            50
        );

    }

    private void renderRightWing(){

        //Right Wing
        shapeRenderer.setColor(shapeColor);
        shapeRenderer.rect(
            origin.x + 25 - 25/2, 
            origin.y - 25/2, 
            25, 
            25
        );

        //Wing Tip
        shapeRenderer.setColor(shapeColor);
        shapeRenderer.rect(
            origin.x + 37 - 15/2, 
            origin.y - 15/2-5, 
            15, 
            15
        );

        //Turbine/Extra Gunner
        shapeRenderer.setColor(Color.ORANGE);
        shapeRenderer.rect(
            origin.x + 25 - 15/2, 
            origin.y - 25+4, 
            15, 
            50
        );

    }

    private void updateHitboxLocation(){

        hitBox = new Rectangle(
            origin.x-35,
            origin.y-35,
            70, 
            70
        );

    }
    
    @Override
    public void shoot() {
        Renderer.aliveBullets.add(new Bullet(
            this,
            new Vector2(origin.x, origin.y-30),
            bulletVelocity
        ));
    }

    @Override
    public void die() {
        isEnabled = false;
        DeathAnim.getDeathAnimation().showDeathAnimation(origin);
    }

    @Override
    public void setProjectionMatrix(Matrix4 newProjectionMatrix) {
        shapeRenderer.setProjectionMatrix(newProjectionMatrix);
    }

}