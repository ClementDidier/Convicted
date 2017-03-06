package com.convicted.game.ui.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.convicted.game.ConvictedGame;
import com.convicted.game.ui.screen.effect.TransitionEffect;

public class MainScreen extends AbstractScreen
{
    private Texture sprite;

    public MainScreen(ConvictedGame game)
    {
        super(game);
        sprite = new Texture(Gdx.files.internal("badlogic.jpg"));
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show()
    {
        final ConvictedGame game = this.getGame();
        this.addAction(Actions.alpha(0, 3f));
        this.addAction(Actions.delay(1,
            Actions.run(new Runnable()
            {
                @Override
                public void run()
                {
                    Gdx.app.log("Screen", "Navigator called");
                    Navigator.navigateTo(game, Scene.GameScreen, TransitionEffect.FadeIn(), TransitionEffect.FadeOut());
                }
            })));
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
        super.draw();
        this.getBatch().begin();
        // TODO : Draw here
        this.getBatch().draw(this.sprite, 100, 100);
        this.getBatch().end();
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
