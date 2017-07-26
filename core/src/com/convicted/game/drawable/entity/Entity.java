package com.convicted.game.drawable.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.convicted.game.drawable.Drawable;

public abstract class Entity implements Drawable, Pool.Poolable
{
    private Vector2 position;

    public Entity()
    {
        this.position = new Vector2();
    }

    public Vector2 getPosition()
    {
        return position;
    }

    public void setPosition(float x, float y)
    {
        this.position.x = x;
        this.position.y = y;
    }
}
