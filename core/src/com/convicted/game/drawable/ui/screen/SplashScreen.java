package com.convicted.game.drawable.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.convicted.game.drawable.entity.character.Monster;
import com.convicted.game.drawable.ui.widget.ProgressBar;
import com.convicted.game.utils.Timer;

import java.util.Random;

import static com.convicted.game.drawable.ui.screen.transition.Transitions.FadeIn;
import static com.convicted.game.drawable.ui.screen.transition.Transitions.FadeOut;

public class SplashScreen extends ConvictedScreen
{
    private Sprite background;
    private ProgressBar bar;
    private Timer timer;

    private Monster grub;
    private Random random;

    public SplashScreen()
    {
        super();
    }

    @Override
    public void load()
    {
        this.assetManager.load("splash.png", Texture.class);
        this.assetManager.load("grub.png", Texture.class);
        this.assetManager.load("progressbar.png", Texture.class);
        this.assetManager.finishLoading();

        this.background = new Sprite(this.assetManager.get("splash.png", Texture.class));

        this.timer = new Timer();
        this.bar = new ProgressBar(this.assetManager.get("progressbar.png", Texture.class));
        this.bar.setMaximum(100);
        this.bar.setPosition(ConvictedScreen.VIEWPORT.x / 2 - this.bar.getWidth() / 2, 260);

        this.grub = new Monster(this.assetManager.get("grub.png", Texture.class));
        this.grub.setPosition(this.bar.getPosition().x, this.bar.getPosition().y);
        this.grub.setScale(6);

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
        this.grub.update(delta);

        if(this.bar.isComplete() && this.timer.ring(100))
        {
            Gdx.app.log("Splash", "ring");
            this.grub.hide();
            ScreenNavigator.navigateTo(ConvictedScreen.MENU, FadeIn(5000), FadeOut(2000));
        }

        if(this.timer.ring(100))
        {
            this.bar.add(this.random.nextInt(10));
            this.grub.setPosition(
                    this.bar.getWidth() * this.bar.getPercent() + this.bar.getPosition().x + this.grub.getRegionWidth() * 3,
                    this.bar.getPosition().y + this.grub.getRegionHeight());
            this.timer.reset();
        }
    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        batch.draw(this.background);
        batch.draw(this.bar);
        batch.draw(this.grub);
    }

    @Override
    public void dispose()
    {
        this.assetManager.dispose();
        super.dispose();
    }
}
