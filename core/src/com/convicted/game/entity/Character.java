package com.convicted.game.entity;

import com.convicted.game.controller.ActorController;

public abstract class Character implements IEntity
{
    private ActorController controller;

    public Character(ActorController controller)
    {
        this.controller = controller;
    }

    public ActorController getController()
    {
        return this.controller;
    }
}
