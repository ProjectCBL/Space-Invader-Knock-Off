package com.mygdx.game.resources.gameobjects;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Bullet extends Entity{

    enum Alignment{
        PLAYER,
        ENEMY
    }

    private float bulletVelocity;
    private Alignment bulletAlignment;

    public Bullet(Entity bulletSummoner, Vector2 spawnPoint, float bulletVelocity){

        System.out.println(String.format(
            "%s fired a bullet...", 
            (bulletSummoner.getClass().getName().equals("com.mygdx.game.resources.gameobjects.Player")) ? "Player" : "Enemy"
        ));

        setBulletAlignment(bulletSummoner.getClass().getName());
        this.bulletVelocity = bulletVelocity;
        origin = spawnPoint;

        shapeRenderer = new ShapeRenderer();

    }

    private void setBulletAlignment(String bulletSummoner) {
        
        switch(bulletSummoner){
            case "com.mygdx.game.resources.gameobjects.Player":
                bulletAlignment = Alignment.PLAYER;
                break;
            case "com.mygdx.game.resources.gameobjects.Enemy":
                bulletAlignment = Alignment.ENEMY;
                break;
            default:
                bulletAlignment = Alignment.PLAYER;
                break;
        }

    }

    @Override
    public void show() {
        
        //shoot();

        shapeRenderer.begin(ShapeType.Filled);

        switch(bulletAlignment){
            case PLAYER:
                showPlayerBulletSprite();
                break;
            case ENEMY:
                showEnemyBulletSprite();
                break;
            default:
                break;
        }

        shapeRenderer.end();

    }

    private void showPlayerBulletSprite(){

        System.out.println("Something");

        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.rect(
            origin.x-5,
            origin.y-15/2+100,
            10,
            15
        );

        shapeRenderer.rect(
            origin.x-4,
            origin.y-15/2+97,
            8,
            15
        );

        shapeRenderer.rect(
            origin.x-2,
            origin.y-15/2+95,
            4,
            15
        );

    }

    private void showEnemyBulletSprite(){

        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(
            origin.x-5,
            origin.y-15/2+100,
            10,
            15
        );

        shapeRenderer.rect(
            origin.x-4,
            origin.y-15/2+103,
            8,
            15
        );

        shapeRenderer.rect(
            origin.x-2,
            origin.y-15/2+105,
            4,
            15
        );

    }

    @Override
    public void shoot() {
        origin.y += bulletVelocity * Gdx.graphics.getDeltaTime();
    }

    @Override
    public void die() {
        // TODO Auto-generated method stub
        System.out.println("Bullet's time is up");
    }

    @Override
    public void setProjectionMatrix(Matrix4 newProjectionMatrix) {
        shapeRenderer.setTransformMatrix(newProjectionMatrix);
    }
    
}
