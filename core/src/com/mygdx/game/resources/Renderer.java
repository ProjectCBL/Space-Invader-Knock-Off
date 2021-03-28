package com.mygdx.game.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.resources.gameobjects.Player;

public class Renderer {

    private static Batch gameBatch;
    private static Renderer renderer = new Renderer();

    private Renderer(){}

    public static Renderer getInstance(){
        return renderer;
    }

    public static void setBatch(Batch newBatch){
        gameBatch = newBatch;
    }

    public void run(){
        clearScreen();
        Controller.getController().controlHandler();
        setAllMatrixes();
        renderGameplayArea();
    }

    private void renderGameplayArea(){
        gameBatch.begin();
        Player.getPlayer().show();
        BulletManager.getManager().showBullets();
        gameBatch.end();
    }

    private void clearScreen(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void setAllMatrixes(){
        Player.getPlayer().setProjectionMatrix(gameBatch.getProjectionMatrix());
        BulletManager.getManager().setBulletMatrixes(gameBatch.getProjectionMatrix());
    }

}
