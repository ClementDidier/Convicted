package com.convicted.game.ui.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.convicted.game.ConvictedGame;
import com.convicted.game.ui.screen.effect.TransitionEffect;

public class TransitionScreen extends AbstractScreen
{
    private AbstractScreen currentScreen;
    private AbstractScreen nextScreen;

    private TransitionEffect currentEffect;
    private TransitionEffect nextEffect;
    private boolean inNextEffect;

    public TransitionScreen(ConvictedGame game,
                            AbstractScreen currentScreen, AbstractScreen nextScreen,
                            TransitionEffect firstScreenEffect, TransitionEffect nextScreenEffect)
    {
        super(game);

        this.currentScreen = currentScreen;
        this.nextScreen = nextScreen;
        this.currentEffect = firstScreenEffect;
        this.nextEffect = nextScreenEffect;
        this.inNextEffect = false;
    }

    @Override
    public void update(float delta)
    {
        if(this.currentEffect != null && !this.currentEffect.isFinished())
        {
            this.currentEffect.render(delta, this.currentScreen);
        }
        else if(!this.inNextEffect)
        {
            this.currentEffect = this.nextEffect;
            this.currentScreen = this.nextScreen;
            this.inNextEffect = true;

            this.currentEffect.render(delta, this.currentScreen);
        }
        else
        {
            this.getGame().setScreen(this.nextScreen);
        }

        this.currentScreen.render(delta);
    }

    @Override
    public void draw(Batch batch)
    {
        this.currentScreen.draw();
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show()
    {

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
