package com.convicted.game.drawable.ui.widget;

import com.badlogic.gdx.math.Vector2;
import com.convicted.game.drawable.Drawable;

public abstract class Widget implements Drawable
{
    protected Vector2 position;

    public Widget(int x, int y)
    {
        this.position = new Vector2(x, y);
    }

    public Widget()
    {
        this(0, 0);
    }

    public Vector2 getPosition()
    {
        return this.position;
    }

    public void setPosition(float x, float y)
    {
        this.position.x = x;
        this.position.y = y;
    }
}
