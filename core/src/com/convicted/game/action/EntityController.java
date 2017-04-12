package com.convicted.game.action;

import com.convicted.game.ui.screen.GameContext;

import java.util.Stack;

public abstract class EntityController
{
    protected Stack<GameAction> actions;
    protected GameContext context;

    public EntityController(GameContext context)
    {
        this.actions = new Stack<GameAction>();
        this.context = context;
    }

    public abstract void act(float delta);
}
