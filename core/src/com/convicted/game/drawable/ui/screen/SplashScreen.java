package com.convicted.game.drawable.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.convicted.game.ConvictedGame;
import com.convicted.game.data.Asset;
import com.convicted.game.data.Configuration;
import com.convicted.game.drawable.entity.character.Monster;
import com.convicted.game.drawable.ui.widget.ProgressBar;
import com.convicted.game.utils.Timer;

import static com.convicted.game.drawable.ui.screen.transition.Transitions.FadeIn;
import static com.convicted.game.drawable.ui.screen.transition.Transitions.FadeOut;

public class SplashScreen extends ConvictedScreen
{
    private Sprite background;
    private ProgressBar bar;
    private Timer timer;
    private Monster grub;

    public SplashScreen(ConvictedGame game)
    {
        super(game);
    }

    @Override
    public void load()
    {
        // Dependances essentielles
        this.game.getAssetManager().load(Asset.SPLASH_BACKGROUND);
        this.game.getAssetManager().load(Asset.SPLASH_PROGRESS_BAR);
        this.game.getAssetManager().load(Asset.GRUB);
        this.game.getAssetManager().finishLoading();


        this.background = new Sprite(this.game.getAssetManager().<Texture>get(Asset.SPLASH_BACKGROUND));

        this.timer = new Timer();
        this.bar = new ProgressBar(this.game.getAssetManager().<Texture>get(Asset.SPLASH_PROGRESS_BAR));
        this.bar.setPosition(
                this.game.getConfiguration().getInteger(Configuration.PREFS_SPLASH_PROGRESS_BAR_ALIGN_X),
                this.game.getConfiguration().getInteger(Configuration.PREFS_SPLASH_PROGRESS_BAR_ALIGN_Y));

        this.grub = new Monster(this.game.getAssetManager().<Texture>get(Asset.GRUB));
        this.grub.setPosition(this.bar.getPosition().x, this.bar.getPosition().y);
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
        this.game.getAssetManager().update((int)(delta * 1000));

        if(this.bar.isComplete() && this.timer.ring(100))
        {
            this.grub.hide();
            ScreenNavigator.navigateTo(ConvictedScreen.MENU, FadeIn(2000), FadeOut(1000));
        }

        if(this.timer.ring(100))
        {
            this.bar.setValue((int)(this.game.getAssetManager().getProgress() * 100));
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
        this.game.getAssetManager().dispose();
        super.dispose();
    }
}
