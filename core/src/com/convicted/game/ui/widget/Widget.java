package com.convicted.game.ui.widget;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public abstract class Widget
{
    private Vector2 origin;

    public Widget(int x, int y)
    {
        this.origin = new Vector2(x, y);
    }

    public Vector2 getOrigin()
    {
        return this.origin;
    }

    public void setOrigin(Vector2 origin)
    {
        this.origin = origin;
    }

    public abstract void draw(Batch batch);
    public abstract InputProcessor getProcessor();
}
