package com.convicted.game.drawable.ui.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.convicted.game.drawable.Drawable;

public abstract class ConvictedScreen implements com.badlogic.gdx.Screen, Drawable
{
    public final static MainScreen MENU = new MainScreen();
    public final static GameScreen GAME = new GameScreen();
    protected Batch batch;

    protected ConvictedScreen()
    {
        this.batch = new SpriteBatch();
    }

    public final void setAlpha(float alpha)
    {
        Color color = this.batch.getColor();
        this.batch.setColor(color.r, color.g, color.b, alpha);
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

        this.batch.begin();
        this.draw(this.batch);
        this.batch.end();
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
}
