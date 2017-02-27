package com.convicted.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.convicted.game.widget.Joystick;

public class ConvictedGame extends ApplicationAdapter {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Joystick joystick;
	
	@Override
	public void create () {

		this.batch = new SpriteBatch();

		this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		this.joystick = new Joystick(
				Gdx.graphics.getWidth() / 5,
				Gdx.graphics.getHeight() / 4);

		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(this.joystick);

		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render ()
	{
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 10, 100);
		batch.end();

		this.joystick.draw(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}
