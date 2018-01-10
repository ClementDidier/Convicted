package com.convicted.game.drawable.environment;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.convicted.game.drawable.Drawable;
import com.convicted.game.drawable.ui.screen.ConvictedBatch;

public class Tile implements Drawable
{
    public final static Vector2 SIZE = new Vector2(96, 96);

    private Sprite sprite;

    public Tile(float x, float y, Texture texture)
    {
        this.sprite = new Sprite(texture);
        this.sprite.setPosition(x, y);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(ConvictedBatch batch)
    {
        batch.draw(sprite);
    }
}
