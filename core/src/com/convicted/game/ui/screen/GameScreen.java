package com.convicted.game.ui.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.convicted.game.ConvictedGame;
import com.convicted.game.action.PlayerController;
import com.convicted.game.entity.Player;
import com.convicted.game.ui.widget.SampleJoystick;

public class GameScreen extends AbstractScreen
{
    private SampleJoystick movementJoystick;
    private SampleJoystick fireJoystick;
    private Player player;

    public GameScreen(ConvictedGame game)
    {
        super(game);
        this.movementJoystick = new SampleJoystick((int)this.getViewport().getWorldWidth() / 8, (int)this.getViewport().getScreenHeight() * 1 / 8);
        this.fireJoystick = new SampleJoystick((int)this.getViewport().getWorldWidth() * 7 / 8, (int)this.getViewport().getScreenHeight() * 1 / 8);
        this.movementJoystick.setScale(0.8f);
        this.fireJoystick.setScale(0.8f);

        Texture texture = new Texture(Gdx.files.internal("charset.png"));

        this.player = new Player(texture);
        PlayerController controller = new PlayerController(player, movementJoystick, fireJoystick);
        player.setController(controller);
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show()
    {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(this.movementJoystick.getProcessor());
        inputMultiplexer.addProcessor(this.fireJoystick.getProcessor());
        Gdx.input.setInputProcessor(inputMultiplexer);

        this.addActor(this.player);
        this.addActor(this.movementJoystick);
        this.addActor(this.fireJoystick);
    }

    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta)
    {
        super.render(delta);
    }

    @Override
    public void draw()
    {
        //super.draw();
    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height)
    {
        this.getViewport().update(width, height, true);
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
