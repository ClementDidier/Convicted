package com.convicted.game.drawable.ui.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.convicted.game.drawable.Drawable;

public abstract class ConvictedScreen implements com.badlogic.gdx.Screen, Drawable
{
    private final static Color CLEAR_COLOR = new Color(Color.WHITE);
    private final static Vector2 VIEWPORT = new Vector2(1280, 720);

    public final static MainScreen MENU = new MainScreen();
    public final static GameScreen GAME = new GameScreen();

    protected ConvictedBatch batch;
    protected OrthographicCamera camera;
    protected Viewport viewport;

    protected ConvictedScreen()
    {
        this.batch = new ConvictedBatch();
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(VIEWPORT.x, VIEWPORT.y, camera);
        this.viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        this.viewport.apply();
    }


    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public final void render(float delta)
    {
        this.update(delta);

        this.camera.update();

        Gdx.gl.glClearColor( 0f, 0f, 0f, 1f);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        batch.setProjectionMatrix(this.camera.combined);
        batch.begin();
        this.draw(batch);
        batch.end();
    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height)
    {

    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause()
    {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume()
    {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide()
    {

    }

    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose()
    {
        if(this.batch != null)
            this.batch.dispose();
    }

    public ConvictedBatch getBatch()
    {
        return this.batch;
    }
}
