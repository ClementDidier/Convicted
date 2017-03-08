package com.convicted.game.action;

import com.convicted.game.entity.Character;

public abstract class EntityController
{
    protected Character actor;

    public EntityController(Character actor)
    {
        this.actor = actor;
    }

    public abstract void act(float delta);
    public abstract GameAction consumeAction();
}
