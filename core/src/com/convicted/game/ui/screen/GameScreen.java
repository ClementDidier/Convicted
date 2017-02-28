package com.convicted.game.ui.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.convicted.game.ui.widget.SampleJoystick;

public class GameScreen extends AbstractScreen
{
    private SampleJoystick joystick;

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show()
    {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();

        this.joystick = new SampleJoystick(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() * 2 / 7);
        inputMultiplexer.addProcessor(this.joystick.getProcessor());

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.getBatch().begin();
        // TODO : Draw here
        this.getBatch().end();

        this.joystick.draw(this.getBatch());
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
}
