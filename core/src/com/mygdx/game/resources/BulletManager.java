package com.mygdx.game.resources;

import java.util.ArrayList;
import com.badlogic.gdx.math.Matrix4;
import com.mygdx.game.resources.gameobjects.Bullet;

public class BulletManager {

    public ArrayList<Bullet> aliveBullets = new ArrayList<Bullet>(); 

    public BulletManager(){}

    public void setBulletMatrixes(Matrix4 newProjectionMatrix){

        for(Bullet obj : aliveBullets){
            obj.setProjectionMatrix(newProjectionMatrix);
        }

    }

    public void showBullets(){

        for(Bullet obj : aliveBullets){
            obj.show();
        }

    }

}
