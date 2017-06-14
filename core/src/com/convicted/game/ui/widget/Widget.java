package com.convicted.game.ui.widget;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Widget extends Actor
{
    private Vector2 origin;

    public Widget(int x, int y)
    {
        this.setX(x);
        this.setY(y);
    }

    public abstract InputProcessor getProcessor();
}
