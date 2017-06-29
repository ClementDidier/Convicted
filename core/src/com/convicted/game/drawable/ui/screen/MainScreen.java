package com.convicted.game.drawable.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static com.convicted.game.drawable.ui.screen.transition.TransitionEffect.FadeIn;
import static com.convicted.game.drawable.ui.screen.transition.TransitionEffect.FadeOut;

public class MainScreen extends ConvictedScreen
{
    private Sprite sprite;

    public MainScreen()
    {
        super();
    }

    @Override
    public void load()
    {
        Gdx.app.log("MainScreen", "load");
        this.sprite = new Sprite(new Texture(Gdx.files.internal("unknow.png")));
    }

    @Override
    public void show()
    {
        Gdx.app.log("MenuScreen", "shown");
    }

    @Override
    public void update(float delta)
    {
        ScreenNavigator.navigateTo(ConvictedScreen.GAME, FadeIn(1f), FadeOut(5f));
    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        batch.draw(this.sprite);
    }

    @Override
    public void dispose()
    {
        this.sprite.getTexture().dispose();
        super.dispose();
    }
}
