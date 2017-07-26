package com.convicted.game.drawable.ui.screen;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.convicted.game.drawable.Drawable;
import com.convicted.game.drawable.ui.widget.Widget;

public class ConvictedBatch extends SpriteBatch
{
    // TODO : Why when alpha isn't static, the variable resetting to its default's value ?
    private static float alpha = 1f;

    public ConvictedBatch()
    {
        super();
    }

    public void draw(Sprite sprite)
    {
        sprite.draw(this, alpha);
    }

    public void draw(Drawable drawable)
    {
        drawable.draw(this);
    }

    public void setAlpha(float a)
    {
        alpha = a;
    }

    public float getAlpha()
    {
        return alpha;
    }
}
