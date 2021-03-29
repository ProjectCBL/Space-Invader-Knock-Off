package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.values.MeshSpawnShapeValue.Triangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.resources.GarbageCollector;
import com.mygdx.game.resources.Renderer;

public class Space extends ApplicationAdapter {
	
	SpriteBatch batch;
	Triangle triangle;
	ShapeRenderer shapeRenderer;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Renderer.setBatch(batch);
	}

	@Override
	public void render () {
		Renderer.getInstance().run();
		GarbageCollector.getCollector().clean();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
