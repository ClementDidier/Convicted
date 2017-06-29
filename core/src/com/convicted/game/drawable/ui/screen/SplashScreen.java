package com.convicted.game.drawable.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.convicted.game.drawable.ui.screen.transition.TransitionEffect;
import com.convicted.game.drawable.ui.widget.ProgressBar;
import com.convicted.game.utils.Timer;

import java.util.Random;

public class SplashScreen extends ConvictedScreen
{
    private Sprite background;
    private ProgressBar bar;
    private Timer timer;

    private Random random;

    public SplashScreen()
    {
        super();
    }

    @Override
    public void load()
    {
        this.background = new Sprite(new Texture(Gdx.files.internal("splash.png")));

        this.timer = new Timer();
        this.bar = new ProgressBar();
        this.bar.setMaximum(500);
        this.bar.setPosition(ConvictedScreen.VIEWPORT.x / 2 - this.bar.getWidth() / 2, 260);
        this.random = new Random();
    }

    @Override
    public void show()
    {

    }

    @Override
    public void update(float delta)
    {
        this.timer.update(delta);
        this.bar.update(delta);

        if(this.bar.isComplete() && this.timer.ring(100))
        {
            Gdx.app.log("Splash", "ring");
            ScreenNavigator.navigateTo(ConvictedScreen.MENU, TransitionEffect.FadeIn(4), TransitionEffect.FadeOut(2));
        }

        if(this.timer.ring(100))
        {
            this.bar.add(this.random.nextInt(10));
            this.timer.reset();
        }
    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        batch.draw(this.background);
        batch.draw(this.bar);
    }

    @Override
    public void dispose()
    {
        super.dispose();
        this.background.getTexture().dispose();
        this.bar.dispose();
    }
}
