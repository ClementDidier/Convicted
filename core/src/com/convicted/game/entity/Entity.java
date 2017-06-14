package com.convicted.game.entity;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.convicted.game.ui.screen.GameContext;

public abstract class Entity extends Actor
{
    private GameContext context;
    protected Sprite sprite;
    public float speed;


    public Entity(Texture texture, GameContext context)
    {
        super();

        this.sprite = new Sprite(texture);
        this.speed = 0;
        this.context = context;
    }

    public GameContext getContext()
    {
        return this.context;
    }
}
