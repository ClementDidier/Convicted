package com.convicted.game.drawable.ui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GameScreen extends ConvictedScreen
{
    private Sprite sprite;

    public GameScreen()
    {
        super();
        this.sprite = new Sprite(new Texture(Gdx.files.internal("rogue.png")));
    }

    @Override
    public void show()
    {

    }

    @Override
    public void update(float delta)
    {

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
