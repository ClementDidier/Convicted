package com.convicted.game.drawable.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.convicted.game.drawable.ui.screen.effect.Transition;

public class MainScreen extends ConvictedScreen
{
    private Sprite sprite;

    public MainScreen()
    {
        super();
        this.sprite = new Sprite(new Texture(Gdx.files.internal("unknow.png")));
    }

    @Override
    public void show()
    {

    }

    @Override
    public void update(float delta)
    {
        ScreenNavigator.navigateTo(ConvictedScreen.GAME, Transition.FADE_IN, Transition.FADE_OUT);
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
    }
}
