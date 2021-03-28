package com.mygdx.game.resources;

import java.util.ArrayList;
import com.badlogic.gdx.math.Matrix4;
import com.mygdx.game.resources.gameobjects.Bullet;

public class BulletManager {
    
    private static BulletManager bulletManager = new BulletManager();

    public static ArrayList<Bullet> aliveBullets = new ArrayList<Bullet>(); 

    private BulletManager(){}

    public static BulletManager getManager(){
        return bulletManager;
    }

    public void setBulletMatrixes(Matrix4 newProjectionMatrix){

        for(Bullet obj : aliveBullets){
            obj.setProjectionMatrix(newProjectionMatrix);
        }

    }

    public void showBullets(){

        for(Bullet obj : aliveBullets){
            obj.show();
            System.out.println(obj.origin);
        }

    }

}
