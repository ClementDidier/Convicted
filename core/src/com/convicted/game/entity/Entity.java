package com.convicted.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Entity extends Actor
{
    protected Sprite sprite;
    public float speed;

    public Entity(Texture texture)
    {
        super();

        this.sprite = new Sprite(texture);
        this.speed = 0;
    }
}
