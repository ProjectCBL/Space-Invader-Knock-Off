package com.mygdx.game.resources.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    public Vector2 origin;
    public Color shapeColor;
    public Rectangle hitBox;
    public boolean isEnabled;
    protected ShapeRenderer shapeRenderer;

    public abstract void show();
    public abstract void shoot();
    public abstract void die();
    public abstract void setProjectionMatrix(Matrix4 newProjectionMatrix);

}
