package com.mygdx.game.resources.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class DeathAnim {
    
    /* Design Specification
    * This is a singleton that has one purpose, and that is to produce a radial animation when
    * the player dies in the game.  This is purely to keep the player class orangized and to not
    * overflow the player class with variables that only pertain to one aspect of the player object.
    */

    private float timer;
    private int distance;
    private int loopCount;
    private static ShapeRenderer shapeRenderer;
    private static DeathAnim deathAnim = new DeathAnim();

    private DeathAnim(){
        timer = 0.0f;
        distance = 15;
    }

    public static DeathAnim getDeathAnimation(){
        return deathAnim;
    }

    public static void setShapRenderer(ShapeRenderer renderer){
        shapeRenderer = renderer;
    }

    private void showBasicSprite(Vector2 origin, int increment){

        shapeRenderer.rect(origin.x + 20 + (increment + 5), origin.y, 5, 3, 10, 6, 1.0f, 1.0f, 0f);

        shapeRenderer.rect(origin.x + 15 + increment, origin.y + 15 + increment, 5, 3, 10, 6, 1.0f, 1f, 45f);

        shapeRenderer.rect(origin.x, origin.y + 20 + (increment + 5), 5, 3, 10, 6, 1.0f, 1f, 90f);

        shapeRenderer.rect(origin.x - 15 - increment, origin.y + 15 + increment, 5, 3, 10, 6, 1.0f, 1f, 135f);

        shapeRenderer.rect(origin.x - 20 - (increment + 5), origin.y, 5, 3, 10, 6, 1.0f, 1f, 180f);

        shapeRenderer.rect(origin.x - 15 - increment, origin.y - 15 - increment, 5, 3, 10, 6, 1.0f, 1f, 225f);

        shapeRenderer.rect(origin.x, origin.y - 20 - (increment + 5), 5, 3, 10, 6, 1.0f, 1f, 270f);

        shapeRenderer.rect(origin.x + 15 + increment, origin.y - 15 - increment, 5, 3, 10, 6, 1.0f, 1f, 315f);

    }

    public void showDeathAnimation(Vector2 origin){

        if (loopCount < 2){

            increaseDistance();

            for(int i = 0; i < distance; i += 15){
                showBasicSprite(origin, i);
            }

            if(distance > 45){
                loopCount += 1;
                distance = 15;
                timer = 0.0f;
            }

        }

    }

    public void increaseDistance(){

        if(distance < 60){

            timer += Gdx.graphics.getDeltaTime();

            if(timer >= 0.2f){
                distance += 15;
                timer = 0.0f;
            }

        }

    }

}
